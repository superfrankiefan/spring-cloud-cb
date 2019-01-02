package com.frankie.demo.service;

import com.frankie.demo.clients.OrganizationDiscoveryClient;
import com.frankie.demo.clients.OrganizationFeignClient;
import com.frankie.demo.clients.OrganizationRestTemplateClient;
import com.frankie.demo.config.ServiceConfig;
import com.frankie.demo.dao.LicenseRepository;
import com.frankie.demo.model.License;
import com.frankie.demo.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicenseService {
    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    ServiceConfig config;

    @Autowired
    OrganizationFeignClient organizationFeignClient;

    @Autowired
    OrganizationRestTemplateClient organizationRestClient;

    @Autowired
    OrganizationDiscoveryClient organizationDiscoveryClient;

    @Autowired
    DiscoveryClient discoveryClient;


    private Organization retrieveOrgInfo(Long organizationId, String clientType) {
        Organization organization = null;

        switch (clientType) {
            case "feign":
                System.out.println("I am using the feign client");
                organization = organizationFeignClient.getOrganization(organizationId);
                break;
            case "rest":
                System.out.println("I am using the rest client");
                organization = organizationRestClient.getOrganization(organizationId);
                break;
            case "discovery":
                System.out.println("I am using the discovery client");
                organization = organizationDiscoveryClient.getOrganization(organizationId);
                break;
            default:
                organization = organizationFeignClient.getOrganization(organizationId);
        }

        return organization;
    }

    public License getLicense(Long organizationId, Long licenseId, String clientType) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);

        Organization org = retrieveOrgInfo(organizationId, clientType);

        return license
                .withOrganizationName(org.getName())
                .withContactName(org.getContactName())
                .withContactEmail(org.getContactEmail())
                .withContactPhone(org.getContactPhone())
                .withComment(config.getExampleProperty());
    }

    public List<License> getLicensesByOrg(Long organizationId) {
        return licenseRepository.findByOrganizationId(organizationId);
    }

    public void saveLicense(License license) {
        licenseRepository.save(license);

    }

    public void updateLicense(License license) {
        licenseRepository.save(license);
    }

    public void deleteLicense(License license) {
        licenseRepository.delete(license);
    }

}
