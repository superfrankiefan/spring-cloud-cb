package com.frankie.demo.controllers;

import com.frankie.demo.model.OrganizationNew;
import com.frankie.demo.services.OrganizationNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="v1/organizationsplat")
public class OrganizationNewServiceController {
    @Autowired
    private OrganizationNewService organizationPlatService;

    @RequestMapping(value="/{organizationId}",method = RequestMethod.GET)
    public OrganizationNew getOrganization(@PathVariable("organizationId") Long organizationId) {
        System.out.println("test");
        OrganizationNew result = organizationPlatService.getOrg(organizationId);
        System.out.println(result);
        return result;
    }

    @RequestMapping(value="/update/{organizationId}/update",method = RequestMethod.PUT)
    public void updateOrganization(@RequestBody OrganizationNew org) {
        organizationPlatService.updateOrg(org);
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    public void saveOrganization(@RequestBody OrganizationNew org) {
        organizationPlatService.saveOrg( org );
    }

    @RequestMapping(value="/delete/{organizationId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@RequestBody OrganizationNew org) {
        organizationPlatService.deleteOrg(org);
    }
}
