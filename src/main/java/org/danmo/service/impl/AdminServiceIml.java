package org.danmo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.danmo.domain.entity.ResourceAllocation;
import org.danmo.service.AdminService;
import org.danmo.service.ResourceAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceIml implements AdminService {
    @Autowired
    private ResourceAllocationService allocationService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resourceAllocation(String higherId, List<String> lowerIds, Integer type) {
        List<ResourceAllocation> olds = allocationService.list(Wrappers.<ResourceAllocation>lambdaQuery()
                .eq(ResourceAllocation::getHigherId, higherId)
                .eq(ResourceAllocation::getType, type));
        if (!CollectionUtils.isEmpty(olds)) {
            allocationService.removeBatchByIds(olds);
        }
        List<ResourceAllocation> allocations = lowerIds.stream().map(item -> {
            return new ResourceAllocation(higherId, item, type);
        }).collect(Collectors.toList());
        allocationService.saveBatch(allocations);
    }
}
