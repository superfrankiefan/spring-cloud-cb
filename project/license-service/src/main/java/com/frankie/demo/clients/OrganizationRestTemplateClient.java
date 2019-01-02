package com.frankie.demo.clients;

import com.frankie.demo.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrganizationRestTemplateClient {
    @Autowired
    RestTemplate restTemplate;

    public Organization getOrganization(Long organizationId){
        ResponseEntity<Organization> restExchange =
                restTemplate.exchange(
                        "http://organizationservice/v1/organizationsplat/{organizationId}",
                        HttpMethod.GET,
                        null, Organization.class, organizationId);
        return restExchange.getBody();
    }
}
