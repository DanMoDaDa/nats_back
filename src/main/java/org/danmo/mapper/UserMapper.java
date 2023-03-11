package org.danmo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.danmo.domain.entity.Privilege;
import org.danmo.domain.entity.Role;
import org.danmo.domain.entity.User;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<Role> getRoles(String id);

    List<Privilege> getPrivileges(String id);

    List<User> getSuperiors();

    List<User> getUnderlings(String superiorId);
}
