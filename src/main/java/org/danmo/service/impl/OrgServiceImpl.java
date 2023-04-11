package org.danmo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.danmo.domain.entity.Org;
import org.danmo.mapper.OrgMapper;
import org.danmo.service.OrgService;
import org.springframework.stereotype.Service;

@Service
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org> implements OrgService {


}
