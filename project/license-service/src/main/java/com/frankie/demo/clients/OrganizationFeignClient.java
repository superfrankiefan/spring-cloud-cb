package com.frankie.demo.clients;

import com.frankie.demo.model.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("organizationservice")
public interface OrganizationFeignClient {
    @RequestMapping(
            method= RequestMethod.GET,
            value="/v1/organizationsplat/{organizationId}",
            consumes="application/json")
    Organization getOrganization(@PathVariable("organizationId") Long organizationId);
}
