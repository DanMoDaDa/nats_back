package org.danmo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.danmo.domain.dto.UserDto;
import org.danmo.domain.entity.Privilege;
import org.danmo.domain.entity.Role;
import org.danmo.domain.entity.User;
import org.danmo.domain.vo.UserVo;
import org.danmo.mapper.UserMapper;
import org.danmo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public List<User> getList(UserDto dto) {
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();
        query.ne(User::getUsername, "admin");
        if(!StringUtils.isEmpty(dto.getNickname()) && !"".equals(dto.getNickname())) {
            query.like(User::getNickname , dto.getNickname());
        }
        if(!StringUtils.isEmpty(dto.getUsername()) && !"".equals(dto.getUsername())) {
            query.like(User::getUsername , dto.getUsername());
        }
        if(!StringUtils.isEmpty(dto.getEmail()) && !"".equals(dto.getEmail())) {
            query.like(User::getEmail , dto.getEmail());
        }
        if(!StringUtils.isEmpty(dto.getPhone()) && !"".equals(dto.getPhone())) {
            query.like(User::getPhone , dto.getPhone());
        }
        if(!StringUtils.isEmpty(dto.getWeixin()) && !"".equals(dto.getWeixin())) {
            query.like(User::getWeixin , dto.getWeixin());
        }
        List<User> users = this.list(query);
        return users;
    }

    @Override
    public List<Role> getRoles(String id) {
        return this.baseMapper.getRoles(id);
    }

    @Override
    public List<Privilege> getPricileges(String id) {
        return this.baseMapper.getPrivileges(id);
    }

    @Override
    public List<UserVo> getSuperiors() {
        List<User> superiors = this.baseMapper.getSuperiors();
        List<UserVo> vos = this.toUserVo(superiors);
        vos.forEach(item -> item.setUnderlings(this.getUnderlings(item.getId())));
        return vos;
    }


    @Override
    public List<UserVo> getUnderlings(String superiorId) {
        List<User> underlings = this.baseMapper.getUnderlings(superiorId);
        List<UserVo> vos = this.toUserVo(underlings);
        return vos;
    }

    @Override
    public List<UserVo> toUserVo(List<User> users) {
        List<UserVo> vos = users.stream().map(item -> {
            UserVo vo = new UserVo();
            BeanUtils.copyProperties(item , vo);
            vo.setRoles(this.getRoles(item.getId()));
            vo.setPrivileges(this.getPricileges(item.getId()));
            return vo;
        }).collect(Collectors.toList());
        return vos;
    }

}
