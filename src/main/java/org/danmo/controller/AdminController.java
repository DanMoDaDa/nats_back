package org.danmo.controller;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.danmo.domain.AjaxResult;
import org.danmo.domain.dto.PrivilegeDto;
import org.danmo.domain.dto.RoleDto;
import org.danmo.domain.dto.UserDto;
import org.danmo.domain.entity.Privilege;
import org.danmo.domain.entity.Role;
import org.danmo.domain.entity.User;
import org.danmo.domain.vo.RoleVo;
import org.danmo.domain.vo.UserVo;
import org.danmo.service.AdminService;
import org.danmo.service.PrivilegeService;
import org.danmo.service.RoleService;
import org.danmo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PrivilegeService privilegeService;

    @PostMapping("/list/user")
    public AjaxResult userList(@RequestBody UserDto dto){
        List<User> users = userService.getList(dto);
        return AjaxResult.success(users);
    }

    @PostMapping("/List/user")
    public AjaxResult getUsers(@RequestBody UserDto dto){
        List<User> users = userService.getList(dto);
        List<UserVo> vos = userService.toUserVo(users);
        return AjaxResult.success(vos);
    }

    @PostMapping("/save/user")
    public AjaxResult saveUser(@RequestBody User user){
        if(StringUtils.isEmpty(user.getId()))
            user.setPassword(SecureUtil.md5("hjt6666"));
        return userService.saveOrUpdate(user)?AjaxResult.success():AjaxResult.error();
    }

    @PostMapping("/delete/user")
    public AjaxResult deleteUser(@RequestBody User user){
        return userService.removeById(user)?AjaxResult.success():AjaxResult.error();
    }

    @PostMapping("/update/user")
    public AjaxResult updateUser(@RequestBody User user){
        return userService.updateById(user)?AjaxResult.success():AjaxResult.error();
    }

    @PostMapping("/list/role")
    public AjaxResult roleList(@RequestBody RoleDto dto){
        List<Role> roles = roleService.getList(dto);
        return AjaxResult.success(roles);
    }

    @PostMapping("/List/role")
    public AjaxResult getRoles(@RequestBody RoleDto dto){
        List<Role> roles = roleService.getList(dto);
        List<RoleVo> vos = roles.stream().map(item -> {
            RoleVo vo = new RoleVo();
            BeanUtils.copyProperties(item , vo);
            vo.setPrivileges(roleService.getPricileges(item.getId()));
            return vo;
        }).collect(Collectors.toList());
        return AjaxResult.success(vos);
    }

    @PostMapping("/save/role")
    public AjaxResult saveRole(@RequestBody Role role){
        return roleService.saveOrUpdate(role)?AjaxResult.success():AjaxResult.error();
    }

    @PostMapping("/delete/role")
    public AjaxResult deleteRole(@RequestBody Role role){
        role.setDelFlag(true);
        return roleService.updateById(role)?AjaxResult.success():AjaxResult.error();
    }

    @PostMapping("/update/role")
    public AjaxResult updateRole(@RequestBody Role role){
        return roleService.updateById(role)?AjaxResult.success():AjaxResult.error();
    }

    @PostMapping("/list/privilege")
    public AjaxResult privilegeList(@RequestBody PrivilegeDto dto){
        List<Privilege> users = privilegeService.getList(dto);
        return AjaxResult.success(users);
    }

    @PostMapping("/save/privilege")
    public AjaxResult savePrivilege(@RequestBody Privilege privilege){
        return privilegeService.saveOrUpdate(privilege)?AjaxResult.success():AjaxResult.error();
    }

    @PostMapping("/delete/privilege")
    public AjaxResult deletePrivilege(@RequestBody Privilege privilege){
        return privilegeService.updateById(privilege)?AjaxResult.success():AjaxResult.error();
    }

    @PostMapping("/update/privilege")
    public AjaxResult updatePrivilege(@RequestBody Privilege privilege){
        return privilegeService.updateById(privilege)?AjaxResult.success():AjaxResult.error();
    }

    @PostMapping("/assign/roles")
    public AjaxResult assignRoles(@RequestBody Map<String,Object> map){
        String userId = (String) map.get("userId");
        List<String> roleIds = (List<String>) map.get("roleIds");
        try {
            adminService.resourceAllocation(userId , roleIds , 0);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    @PostMapping("/assign/privileges")
    public AjaxResult assignPrivileges(@RequestBody Map<String,Object> map){
        String roleId = (String) map.get("roleId");
        List<String> privilegeIds = (List<String>) map.get("privilegeIds");
        try {
            adminService.resourceAllocation(roleId , privilegeIds , 1);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    @PostMapping("/assign/underlings")
    public AjaxResult assignUnderlings(@RequestBody Map<String,Object> map){
        String roleId = (String) map.get("superiorId");
        List<String> underlingIds = (List<String>) map.get("underlingIds");
        try {
            adminService.resourceAllocation(roleId , underlingIds , 2);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

}
