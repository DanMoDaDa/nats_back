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

    private int value;

    AuditTypeEnum(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
