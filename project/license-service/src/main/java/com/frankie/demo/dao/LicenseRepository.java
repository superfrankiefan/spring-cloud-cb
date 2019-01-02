package com.frankie.demo.dao;

import com.frankie.demo.model.License;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenseRepository extends CrudRepository<License, Integer> {

    public License findByOrganizationIdAndLicenseId(Long organizationId, Long licenseId);

    public List<License> findByOrganizationId(Long organizationId);

}
