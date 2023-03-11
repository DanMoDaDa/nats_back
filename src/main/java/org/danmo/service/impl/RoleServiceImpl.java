package org.danmo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.danmo.domain.dto.RoleDto;
import org.danmo.domain.entity.Privilege;
import org.danmo.domain.entity.Role;
import org.danmo.mapper.RoleMapper;
import org.danmo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public List<Role> getList(RoleDto dto) {
        LambdaQueryWrapper<Role> query = Wrappers.lambdaQuery();
        query.ne(Role::getRoleCode, "SUPER-ADMIN");
        if(!StringUtils.isEmpty(dto.getRoleName()) && !"".equals(dto.getRoleName()))
            query.like(Role::getRoleName , dto.getRoleName());
        if(!StringUtils.isEmpty(dto.getRoleCode()) && !"".equals(dto.getRoleCode()))
            query.like(Role::getRoleCode , dto.getRoleCode());
        List<Role> Roles = this.list(query);
        return Roles;
    }

    @Override
    public List<Privilege> getPricileges(String id) {
        return this.baseMapper.getPrivileges(id);
    }
}
