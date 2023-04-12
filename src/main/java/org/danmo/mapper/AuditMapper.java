package org.danmo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.danmo.domain.entity.Audit;

@Mapper
public interface AuditMapper extends BaseMapper<Audit> {

}
