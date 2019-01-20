package com.frankie.demo.service;

import com.frankie.demo.dao.OrganizationPlatRepository;
import com.frankie.demo.model.OrganizationPlat;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationPlatService {
    @Autowired
    private OrganizationPlatRepository organizationPlatRepository;

    @HystrixCommand(commandProperties =
            {@HystrixProperty(
                    name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "12000")})
    public OrganizationPlat getOrg(Long organizationId) {
        return organizationPlatRepository.findById(organizationId);
    }

    public void saveOrg(OrganizationPlat org) {
        organizationPlatRepository.save(org);
    }

    public void updateOrg(OrganizationPlat org) {
        organizationPlatRepository.save(org);
    }

    public void deleteOrg(OrganizationPlat org) {
        organizationPlatRepository.delete(org);
    }
}
