package org.danmo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.danmo.domain.entity.base.BaseEntity;

@Mapper
public interface TestMapper extends BaseMapper<BaseEntity> {
}
