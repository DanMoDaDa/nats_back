package org.danmo.controller;

import org.danmo.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivilegeController {

    @Autowired
    PrivilegeService privilegeService;
}
