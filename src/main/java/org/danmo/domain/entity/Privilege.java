package org.danmo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.danmo.domain.entity.base.BaseEntity;

@Data
@TableName(value = "t_privilege")
public class Privilege extends BaseEntity {

    private String privilegeName;

    private String privilegeCode;
}
