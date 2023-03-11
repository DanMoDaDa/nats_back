package org.danmo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.danmo.domain.dto.RoleDto;
import org.danmo.domain.entity.Privilege;
import org.danmo.domain.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {

    List<Role> getList(RoleDto dto);

    List<Privilege> getPricileges(String id);
}
