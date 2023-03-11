package org.danmo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.danmo.domain.entity.ResourceAllocation;
import org.danmo.mapper.ResourceAllocationMapper;
import org.danmo.service.ResourceAllocationService;
import org.springframework.stereotype.Service;

@Service
public class ResourceAllocationServiceImpl extends ServiceImpl<ResourceAllocationMapper, ResourceAllocation> implements ResourceAllocationService {
}
