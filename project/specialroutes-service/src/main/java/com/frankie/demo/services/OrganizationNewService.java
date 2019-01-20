package com.frankie.demo.services;

import com.frankie.demo.model.OrganizationNew;
import com.frankie.demo.repository.OrganizationNewRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationNewService {
    @Autowired
    private OrganizationNewRepository organizationPlatRepository;

    @HystrixCommand(commandProperties =
            {@HystrixProperty(
                    name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "12000")})
    public OrganizationNew getOrg(Long organizationId) {
        return organizationPlatRepository.findById(organizationId);
    }

    public void saveOrg(OrganizationNew org) {
        organizationPlatRepository.save(org);
    }

    public void updateOrg(OrganizationNew org) {
        organizationPlatRepository.save(org);
    }

    public void deleteOrg(OrganizationNew org) {
        organizationPlatRepository.delete(org);
    }
}
