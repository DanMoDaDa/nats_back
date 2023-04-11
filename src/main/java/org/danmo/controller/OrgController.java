package org.danmo.controller;

import org.danmo.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrgController {

    @Autowired
    private OrgService orgService;

}
