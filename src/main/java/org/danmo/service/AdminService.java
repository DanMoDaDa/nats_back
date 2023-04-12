package org.danmo.service;

import org.danmo.domain.dto.AuditDto;
import org.danmo.domain.dto.OrgDto;
import org.danmo.domain.dto.SampleTakeDto;

import java.util.List;

public interface AdminService {

    boolean resourceAllocation(String higherId, List<String> lowerIds, Integer type);

    boolean saveSamplePoint(SampleTakeDto dto);

    boolean updateAudit(AuditDto auditDto);

    boolean saveOrganization(OrgDto orgDto);

    boolean assignOrganization(List<String> userIds, String orgId);
}
