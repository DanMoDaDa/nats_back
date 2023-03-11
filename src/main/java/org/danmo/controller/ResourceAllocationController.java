package org.danmo.controller;

import org.danmo.domain.AjaxResult;
import org.danmo.service.ResourceAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceAllocationController {

    @Autowired
    private ResourceAllocationService resourceAllocationService;
}
