package org.danmo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.danmo.domain.entity.base.BaseEntity;

/**
 * 采样点
 * @author danmo
 */
@Data
@TableName(value = "t_sample_take")
public class SampleTake extends BaseEntity {

    private String name;

    private String timeSlot;

    private Integer status;

    private String chargePerson;

    private String contact;

    private String address;

    private String location;

    private String appendix;

    private String province;

    private String city;

    private String region;

}
