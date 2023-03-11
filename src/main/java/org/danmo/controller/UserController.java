package org.danmo.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.danmo.cache.LocalCache;
import org.danmo.domain.AjaxResult;
import org.danmo.domain.entity.User;
import org.danmo.domain.vo.UserVo;
import org.danmo.service.UserService;
import org.danmo.utils.JwtUtil;
import org.danmo.utils.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody User user){
        log.info("------------------用户登录，用户信息："+JSONUtil.toJsonStr(user)+"-------------------");
        try {
            User one = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
            if (ObjectUtils.isEmpty(one)) {
                return AjaxResult.error("用户不存在");
            }
            if (!StringUtils.equals(one.getPassword(),SecureUtil.md5(user.getPassword()))) {
                return AjaxResult.error("密码不正确");
            }
            JSONObject entries = JSONUtil.parseObj(JSONUtil.toJsonStr(user));
            String token = JwtUtil.createJWT(TimeUtil.getTokenOutTime(), entries);
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(one,userVo);
            userVo.setToken(token);
            userVo.setRoles(userService.getRoles(one.getId()));
            userVo.setPrivileges(userService.getPricileges(one.getId()));
            LocalCache.put(token,userVo);
            // 暂时感觉没用
//        LocalCache.put(user.getUsername(),token);
            return AjaxResult.success(userVo);
        }catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public AjaxResult logout(HttpServletRequest request){
        String token = request.getHeader("token");
        log.info("----logout{token:"+token+"}----");
        LocalCache.remove(token);
        return AjaxResult.success();
    }

    @PostMapping("/checkToken")
    public AjaxResult checkToken(HttpServletRequest request){
        String token = request.getHeader("token");
        log.info("----checkToken{token:"+token+"}----");
        Boolean flag = JwtUtil.checkToken(token);
        UserVo userVo = (UserVo) LocalCache.get(token);
        return flag?AjaxResult.success(userVo):AjaxResult.error();
    }

    @PostMapping("/list/superior")
    public AjaxResult getSuperiors(){
        List<UserVo> superiors = userService.getSuperiors();
        return AjaxResult.success(superiors);
    }

    @PostMapping("/list/underling")
    public AjaxResult getUnderlings(){
        List<UserVo> underlings = userService.getUnderlings(null);
        return AjaxResult.success(underlings);
    }
}
