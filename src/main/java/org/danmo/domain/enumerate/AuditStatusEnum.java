package org.danmo.domain.enumerate;

public enum AuditStatusEnum {
    /**
     * 待审核
     */
    AWAIT(-1),
    /**
     * 审核通过
     */
    PASS(1),
    /**
     * 审核驳回
     */
    OVERRULE(0);

    private Integer value;

    AuditStatusEnum(Integer value){
        this.value = value;
    }

    public Integer getValue(){
        return value;
    }
}
