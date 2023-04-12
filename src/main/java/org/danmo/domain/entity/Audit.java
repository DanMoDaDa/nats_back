package org.danmo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.danmo.domain.entity.base.BaseEntity;

/**
 * 组织
 * @author danmo
 */
@Data
@TableName(value = "t_audit")
public class Audit extends BaseEntity {

    private String contentId;

    private Integer contentType;

    private Integer status;

    private String remark;

}
