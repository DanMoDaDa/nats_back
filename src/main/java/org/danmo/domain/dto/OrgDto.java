package org.danmo.domain.dto;

import lombok.Data;
import org.danmo.domain.entity.Appendix;
import org.danmo.domain.entity.Org;

import java.util.List;

@Data
public class OrgDto extends Org {

    /**
     * 附件集合
     */
    List<Appendix> appendixs;

}
