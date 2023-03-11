package org.danmo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.danmo.domain.entity.base.BaseEntity;
import org.danmo.mapper.TestMapper;
import org.springframework.stereotype.Service;

@Service
public class TestService extends ServiceImpl<TestMapper, BaseEntity> {

}
