package org.danmo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.danmo.domain.entity.Audit;
import org.danmo.mapper.AuditMapper;
import org.danmo.service.AuditService;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl extends ServiceImpl<AuditMapper, Audit> implements AuditService {


}
