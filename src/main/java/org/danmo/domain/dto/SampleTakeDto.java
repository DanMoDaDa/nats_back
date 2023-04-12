package org.danmo.domain.dto;

import lombok.Data;
import org.danmo.domain.entity.Appendix;
import org.danmo.domain.entity.SampleTake;

import java.util.List;

@Data
public class SampleTakeDto extends SampleTake {

    /**
     * 附件集合
     */
    List<Appendix> appendixs;

}
