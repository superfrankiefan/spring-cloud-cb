package com.frankie.demo.controller;

import com.frankie.demo.dao.OrganizationRepository;
import com.frankie.demo.model.Organization;
import com.frankie.demo.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/v1/organizations")
public class OrganizationServiceController {

    @Autowired
    OrganizationService organizationService;

    @Autowired
    OrganizationRepository organizationRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Long insertOrg(@RequestParam("name") String org_name,
                          @RequestParam("contact_name") String contactName,
                          @RequestParam("contact_email") String contactEmail,
                          @RequestParam("contact_phone") String contactPhone){

        return organizationService.addOrg(org_name, contactName, contactEmail, contactPhone);
    }

    @RequestMapping(value="/{organizationId}",method = RequestMethod.GET)
    public Organization getOrganization( @PathVariable("organizationId") Long organizationId) {
        return organizationService.getOrg(organizationId);
    }

    @RequestMapping(value="/{organizationId}",method = RequestMethod.PUT)
    public void updateOrganization( @PathVariable("organizationId") String orgId, @RequestBody Organization org) {
        organizationService.updateOrg( org );
    }

    @RequestMapping(value="/{organizationId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization( @PathVariable("orgId") String orgId,  @RequestBody Organization org) {
        organizationService.deleteOrg( org );
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Organization> listAll(){
        List<Organization> orgs = new LinkedList<Organization>();
        for(Organization org: organizationRepository.findAll()){
            orgs.add(org);
        }
        System.out.println(orgs);
        return orgs;
    }

}
