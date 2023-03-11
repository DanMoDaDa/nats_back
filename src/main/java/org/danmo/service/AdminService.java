package org.danmo.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminService {

    void resourceAllocation(String higherId, List<String> lowerIds, Integer type);

}
