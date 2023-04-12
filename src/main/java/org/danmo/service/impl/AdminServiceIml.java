package org.danmo.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.danmo.domain.dto.AuditDto;
import org.danmo.domain.dto.OrgDto;
import org.danmo.domain.dto.SampleTakeDto;
import org.danmo.domain.entity.*;
import org.danmo.domain.enumerate.AuditStatusEnum;
import org.danmo.domain.enumerate.AuditTypeEnum;
import org.danmo.service.*;
import org.danmo.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceIml implements AdminService {
    @Autowired
    private ResourceAllocationService allocationService;

    @Autowired
    private UserService userService;

    @Autowired
    private SampleTakeService sampleTakeService;

    @Autowired
    private OrgService orgService;

    @Autowired
    private AppendixService appendixService;

    @Autowired
    private AuditService auditService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean resourceAllocation(String higherId, List<String> lowerIds, Integer type) {
        List<ResourceAllocation> olds = allocationService.list(Wrappers.<ResourceAllocation>lambdaQuery()
                .eq(ResourceAllocation::getHigherId, higherId)
                .eq(ResourceAllocation::getType, type));
        if (!CollectionUtils.isEmpty(olds)) {
            allocationService.removeBatchByIds(olds);
        }
        List<ResourceAllocation> allocations = lowerIds.stream().map(item -> {
            return new ResourceAllocation(higherId, item, type);
        }).collect(Collectors.toList());
        return allocationService.saveBatch(allocations);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveSamplePoint(SampleTakeDto dto) {
        SampleTake sampleTake = new SampleTake();
        BeanUtil.copyProperties(dto , sampleTake);
        List<Appendix> appendixs = dto.getAppendixs();
        if(CollectionUtils.isNotEmpty(appendixs)) {
            boolean flag2 = appendixService.saveBatch(appendixs);
            if(!flag2) {
                throw new RuntimeException("新增采样点时，附件保存失败！");
            }
            List<String> ids = appendixs.stream().map(Appendix::getId).collect(Collectors.toList());
            sampleTake.setAppendix(JSONUtil.toJsonStr(ids));
        }
        boolean flag = sampleTakeService.save(sampleTake);
        if(!flag) {
            throw new RuntimeException("新增采样点失败！");
        }
        if(StringUtils.isBlank(dto.getId())) {
            Audit audit = new Audit();
            audit.setContentId(sampleTake.getId());
            audit.setContentType(AuditTypeEnum.SAMPLE_POINTS.getValue());
            audit.setStatus(AuditStatusEnum.AWAIT.getValue());
            boolean flag3 = auditService.save(audit);
            if(!flag3) {
                throw new RuntimeException("新增采样点时，发起审核失败！");
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrganization(OrgDto dto) {
        Org org = new Org();
        BeanUtil.copyProperties(dto , org);
        List<Appendix> appendixs = dto.getAppendixs();
        if(CollectionUtils.isNotEmpty(appendixs)) {
            boolean flag2 = appendixService.saveBatch(appendixs);
            if(!flag2) {
                throw new RuntimeException("新增采样点时，附件保存失败！");
            }
            List<String> ids = appendixs.stream().map(Appendix::getId).collect(Collectors.toList());
            org.setAppendix(JSONUtil.toJsonStr(ids));
        }
        boolean flag = orgService.save(org);
        if(!flag) {
            throw new RuntimeException("新增采样点失败！");
        }
        if(StringUtils.isBlank(dto.getId())) {
            Audit audit = new Audit();
            audit.setContentId(org.getId());
            audit.setContentType(AuditTypeEnum.ORG.getValue());
            audit.setStatus(AuditStatusEnum.AWAIT.getValue());
            boolean flag3 = auditService.save(audit);
            if(!flag3) {
                throw new RuntimeException("新增采样点时，发起审核失败！");
            }
        }
        return true;
    }

    @Override
    public boolean assignOrganization(List<String> userIds, String orgId) {
        List<User> users = userService.getBaseMapper().selectBatchIds(userIds);
        users.forEach(item -> item.setOrgId(orgId));
        boolean flag = userService.saveOrUpdateBatch(users);
        return flag;
    }

    @Override
    public boolean updateAudit(AuditDto auditDto) {
        Audit audit = auditService.getOne(Wrappers.<Audit>lambdaQuery()
                .eq(Audit::getContentId, auditDto.getContentId()));
        BeanUtil.copyPropertiesIgnoreNull(auditDto , audit);
        return auditService.updateById(audit);
    }

}
