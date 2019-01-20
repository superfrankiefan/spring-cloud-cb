package com.frankie.demo.controllers;

import com.frankie.demo.model.AbTestingRoute;
import com.frankie.demo.services.AbTestingRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/route/")
public class SpecialRoutesServiceController {
    @Autowired
    AbTestingRouteService routeService;

    @RequestMapping(value = "abtesting/{serviceName}", method = RequestMethod.GET)
    public AbTestingRoute abstestings(@PathVariable("serviceName") String serviceName) {
        AbTestingRoute result = routeService.getRoute(serviceName);
        return result;
    }

    @RequestMapping(value = "abtesting/{serviceName}", method = RequestMethod.POST)
    public void addRoutes(@PathVariable("serviceName") String serviceName) {
        AbTestingRoute instance = new AbTestingRoute();
        instance.setActive("Y");
        instance.setServiceName(serviceName);
        instance.setWeight(3);
        instance.setEndpoint("http://specialroutesservice");
        routeService.saveAbTestingRoute(instance);
    }
}
