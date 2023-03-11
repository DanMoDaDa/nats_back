package org.danmo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.danmo.domain.dto.UserDto;
import org.danmo.domain.entity.Privilege;
import org.danmo.domain.entity.Role;
import org.danmo.domain.entity.User;
import org.danmo.domain.vo.UserVo;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> getList(UserDto dto);

    /**
     * 根据id查询角色值
     * @param id
     * @return
     */
    List<Role> getRoles(String id);

    /**
     * 根据id查询权限值
     * @param id
     * @return
     */
    List<Privilege> getPricileges(String id);

    List<UserVo> getSuperiors();

    List<UserVo> getUnderlings(String superiorId);

    List<UserVo> toUserVo(List<User> users);
}
