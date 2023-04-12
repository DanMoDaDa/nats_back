package org.danmo.controller;

import org.danmo.domain.AjaxResult;
import org.danmo.domain.dto.PrivilegeDto;
import org.danmo.domain.entity.Privilege;
import org.danmo.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrivilegeController {

    @Autowired
    PrivilegeService privilegeService;

    /**
     * 查询权限列表
     * @param dto
     * @return
     */
    @PostMapping("/list/privilege")
    public AjaxResult privilegeList(@RequestBody PrivilegeDto dto){
        List<Privilege> users = privilegeService.getList(dto);
        return AjaxResult.success(users);
    }

    /**
     * 保存权限
     * @param privilege
     * @return
     */
    @PostMapping("/save/privilege")
    public AjaxResult savePrivilege(@RequestBody Privilege privilege){
        return privilegeService.saveOrUpdate(privilege)?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 删除权限
     * @param privilege
     * @return
     */
    @PostMapping("/delete/privilege")
    public AjaxResult deletePrivilege(@RequestBody Privilege privilege){
        return privilegeService.updateById(privilege)?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 更新权限
     * @param privilege
     * @return
     */
    @PostMapping("/update/privilege")
    public AjaxResult updatePrivilege(@RequestBody Privilege privilege){
        return privilegeService.updateById(privilege)?AjaxResult.success():AjaxResult.error();
    }

}
