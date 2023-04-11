package org.danmo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.danmo.domain.entity.base.BaseEntity;

/**
 * 附件
 * @author danmo
 */
@Data
@TableName(value = "t_appendix")
public class Appendix extends BaseEntity {

    private String name;

    private String type;

    private String url;

    private Long size;

}
