package com.frankie.demo.service;

import com.frankie.demo.dao.OrganizationPlatRepository;
import com.frankie.demo.model.OrganizationPlat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationPlatService {
    @Autowired
    private OrganizationPlatRepository organizationPlatRepository;

    public OrganizationPlat getOrg(Long organizationId) {
        return organizationPlatRepository.findById(organizationId);
    }

    public void saveOrg(OrganizationPlat org){
        organizationPlatRepository.save(org);
    }

    public void updateOrg(OrganizationPlat org){
        organizationPlatRepository.save(org);
    }

    public void deleteOrg(OrganizationPlat org){
        organizationPlatRepository.delete(org);
    }
}
