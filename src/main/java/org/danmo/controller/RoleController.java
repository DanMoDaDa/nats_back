package org.danmo.controller;

import org.danmo.domain.AjaxResult;
import org.danmo.domain.dto.RoleDto;
import org.danmo.domain.entity.Role;
import org.danmo.domain.vo.RoleVo;
import org.danmo.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询角色列表
     * @param dto
     * @return
     */
    @PostMapping("/list/role")
    public AjaxResult roleList(@RequestBody RoleDto dto){
        List<Role> roles = roleService.getList(dto);
        return AjaxResult.success(roles);
    }

    /**
     * 查询角色列表（角色携带权限）
     * @param dto
     * @return
     */
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

    /**
     * 保存角色
     * @param role
     * @return
     */
    @PostMapping("/save/role")
    public AjaxResult saveRole(@RequestBody Role role){
        return roleService.saveOrUpdate(role)?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 删除角色
     * @param role
     * @return
     */
    @PostMapping("/delete/role")
    public AjaxResult deleteRole(@RequestBody Role role){
        role.setDelFlag(true);
        return roleService.updateById(role)?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 更新角色
     * @param role
     * @return
     */
    @PostMapping("/update/role")
    public AjaxResult updateRole(@RequestBody Role role){
        return roleService.updateById(role)?AjaxResult.success():AjaxResult.error();
    }
}
