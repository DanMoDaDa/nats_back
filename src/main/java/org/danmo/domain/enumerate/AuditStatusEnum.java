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

    private int value;

    AuditStatusEnum(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
