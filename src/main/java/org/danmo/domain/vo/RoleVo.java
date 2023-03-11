package org.danmo.domain.vo;

import lombok.Data;
import org.danmo.domain.entity.Privilege;
import org.danmo.domain.entity.Role;
import org.danmo.domain.entity.User;

import java.util.List;

@Data
public class RoleVo extends Role {

    private List<Privilege> privileges;

}
