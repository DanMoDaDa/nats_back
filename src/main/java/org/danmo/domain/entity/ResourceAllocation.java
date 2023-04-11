package org.danmo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.danmo.domain.entity.base.BaseEntity;

/**
 * 资源分配
 * @author danmo
 */
@Data
@TableName(value = "t_resource_allocation")
public class ResourceAllocation extends BaseEntity {

    private String higherId;

    private String lowerId;

    private Integer type;

    public ResourceAllocation(){}

    public ResourceAllocation(String higherId, String lowerId, int type) {
        this.higherId = higherId;
        this.lowerId = lowerId;
        this.type = type;
    }
}
