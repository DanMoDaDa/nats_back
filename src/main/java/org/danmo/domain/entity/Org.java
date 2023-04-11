package org.danmo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.danmo.domain.entity.base.BaseEntity;

/**
 * 组织
 * @author danmo
 */
@Data
@TableName(value = "t_organization")
public class Org extends BaseEntity {

    private String orgName;

    private String orgCode;

    private String adminId;

}
