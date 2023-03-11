package org.danmo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.danmo.domain.dto.PrivilegeDto;
import org.danmo.domain.entity.Privilege;

import java.util.List;

public interface PrivilegeService extends IService<Privilege> {

    List<Privilege> getList(PrivilegeDto dto);
}
