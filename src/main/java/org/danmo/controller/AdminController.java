package org.danmo.controller;

import org.danmo.domain.AjaxResult;
import org.danmo.domain.dto.*;
import org.danmo.domain.entity.User;
import org.danmo.domain.vo.AuditVo;
import org.danmo.domain.vo.UserVo;
import org.danmo.service.AdminService;
import org.danmo.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 分配组织
     * @param map
     * @return
     */
    @PostMapping("/assign/organization")
    public AjaxResult assignOrganization(@RequestBody Map<String,Object> map){
        List<String> userIds = (List<String>) map.get("userIds");
        String orgId = (String) map.get("orgId");
        boolean flag = adminService.assignOrganization(userIds,orgId);
        return flag?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 分配角色
     * @param map
     * @return
     */
    @PostMapping("/assign/roles")
    public AjaxResult assignRoles(@RequestBody Map<String,Object> map){
        String userId = (String) map.get("userId");
        List<String> roleIds = (List<String>) map.get("roleIds");
        try {
            boolean flag = adminService.resourceAllocation(userId, roleIds, 0);
            if(!flag) {
                return AjaxResult.error();
            }
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    /**
     * 分配权限
     * @param map
     * @return
     */
    @PostMapping("/assign/privileges")
    public AjaxResult assignPrivileges(@RequestBody Map<String,Object> map){
        String roleId = (String) map.get("roleId");
        List<String> privilegeIds = (List<String>) map.get("privilegeIds");
        try {
            boolean flag = adminService.resourceAllocation(roleId, privilegeIds, 1);
            if(!flag) {
                return AjaxResult.error();
            }
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    /**
     * 分配下属
     * @param map
     * @return
     */
    @PostMapping("/assign/underlings")
    public AjaxResult assignUnderlings(@RequestBody Map<String,Object> map){
        String roleId = (String) map.get("superiorId");
        List<String> underlingIds = (List<String>) map.get("underlingIds");
        try {
            boolean flag = adminService.resourceAllocation(roleId, underlingIds, 2);
            if(!flag) {
                return AjaxResult.error();
            }
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    /**
     * 保存组织
     * @param orgDto
     * @return
     */
    @PostMapping("/save/organization")
    public AjaxResult saveOrganization(@RequestBody OrgDto orgDto){
        try {
            adminService.saveOrganization(orgDto);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    /**
     * 保存采样点
     * @param sampleTakeDto
     * @return
     */
    @PostMapping("/save/samplePoint")
    public AjaxResult saveSamplePoint(@RequestBody SampleTakeDto sampleTakeDto){
        try {
            adminService.saveSamplePoint(sampleTakeDto);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    /**
     * 更新审核
     * @param auditDto
     * @return
     */
    @PostMapping("/update/audit")
    public AjaxResult updateAudit(@RequestBody AuditDto auditDto){
        boolean flag = adminService.updateAudit(auditDto);
        return flag?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 查询审核
     * @param auditDto
     * @return
     */
    @PostMapping("/List/audit")
    public AjaxResult listAudit(@RequestBody AuditDto auditDto){
        List<AuditVo> audits = adminService.listAudit(auditDto);
        return AjaxResult.success(audits);
    }

    /**
     * 重置密码
     * @param userId
     * @return
     */
    @PostMapping("/reset/password")
    public AjaxResult resetPassword(String userId){
        boolean flag = adminService.resetPassword(userId);
        return flag?AjaxResult.success():AjaxResult.error();
    }

    @PostMapping("/test")
    public AjaxResult test(@RequestBody Map<String,Object> map){
        User user = new User();
        UserVo userVo = new UserVo();
        userVo.setToken("123456");
        userVo.setNickname("ffffff");
        user.setUsername("abcdef");
        BeanUtil.copyProperties(user,userVo);
        userVo.setToken("123456");
        userVo.setNickname("ffffff");
        user.setUsername(null);
        BeanUtil.copyPropertiesIgnoreNull(user,userVo);

        return AjaxResult.success();
    }

}
