package com.frankie.demo.controller;

import com.frankie.demo.model.Organization;
import com.frankie.demo.model.OrganizationPlat;
import com.frankie.demo.service.OrganizationPlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="v1/organizationsplat")
public class OrganizationPlatServiceController {
    @Autowired
    private OrganizationPlatService organizationPlatService;

    @RequestMapping(value="/{organizationId}",method = RequestMethod.GET)
    public OrganizationPlat getOrganization(@PathVariable("organizationId") Long organizationId) {
        return organizationPlatService.getOrg(organizationId);
    }

    @RequestMapping(value="/update/{organizationId}/update",method = RequestMethod.PUT)
    public void updateOrganization(@RequestBody OrganizationPlat org) {
        organizationPlatService.updateOrg(org);
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    public void saveOrganization(@RequestBody OrganizationPlat org) {
        organizationPlatService.saveOrg( org );
    }

    @RequestMapping(value="/delete/{organizationId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@RequestBody OrganizationPlat org) {
        organizationPlatService.deleteOrg(org);
    }

}
