package org.danmo.domain.enumerate;

public enum AuditTypeEnum {
    /**
     * 组织
     */
    ORG(1),
    /**
     * 采样点
     */
    SAMPLE_POINTS(2);

    private Integer value;

    AuditTypeEnum(Integer value){
        this.value = value;
    }

    public Integer getValue(){
        return value;
    }
}
