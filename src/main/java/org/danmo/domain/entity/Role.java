package org.danmo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.danmo.domain.entity.base.BaseEntity;

@Data
@TableName(value = "t_role")
public class Role extends BaseEntity {

    private String roleName;

    private String roleCode;

    private Integer level;
}
