package org.danmo.domain.vo;

import lombok.Data;
import org.danmo.domain.entity.*;

@Data
public class AuditVo extends User {

    private SampleTake sampleTake;

    private Org org;

}
