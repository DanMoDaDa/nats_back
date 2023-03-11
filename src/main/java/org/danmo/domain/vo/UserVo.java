package org.danmo.domain.vo;

import lombok.Data;
import org.danmo.domain.entity.Privilege;
import org.danmo.domain.entity.Role;
import org.danmo.domain.entity.User;

import java.util.List;

@Data
public class UserVo extends User {

    private String token;

    private List<Role> roles;

    private List<Privilege> privileges;

    // 直属上级
    private UserVo superior;

    // 下属
    private List<UserVo> underlings;

}
