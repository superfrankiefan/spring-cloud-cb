package com.frankie.demo.controller;

import com.frankie.demo.service.DiscoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/tools")
public class ToolsController {
    @Autowired
    DiscoveryService discoveryService;

    @RequestMapping(value="/eureka/services",method = RequestMethod.GET)
    public List<String> getEurekaServices() {
        return discoveryService.getEurekaServices();
    }

}
