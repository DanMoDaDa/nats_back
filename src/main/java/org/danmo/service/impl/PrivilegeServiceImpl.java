package org.danmo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.danmo.domain.dto.PrivilegeDto;
import org.danmo.domain.entity.Privilege;
import org.danmo.mapper.PrivilegeMapper;
import org.danmo.service.PrivilegeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegeServiceImpl extends ServiceImpl<PrivilegeMapper, Privilege> implements PrivilegeService {
    @Override
    public List<Privilege> getList(PrivilegeDto dto) {
        LambdaQueryWrapper<Privilege> query = Wrappers.lambdaQuery();
        query.ne(Privilege::getPrivilegeCode, "ALL");
        if(!StringUtils.isEmpty(dto.getPrivilegeName()) && !"".equals(dto.getPrivilegeName()))
            query.like(Privilege::getPrivilegeName , dto.getPrivilegeName());
        if(!StringUtils.isEmpty(dto.getPrivilegeCode()) && !"".equals(dto.getPrivilegeCode()))
            query.like(Privilege::getPrivilegeCode , dto.getPrivilegeCode());
        List<Privilege> Privileges = this.list(query);
        return Privileges;
    }

}
