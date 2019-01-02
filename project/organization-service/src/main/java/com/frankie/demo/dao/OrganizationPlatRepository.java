package com.frankie.demo.dao;

import com.frankie.demo.model.OrganizationPlat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationPlatRepository extends CrudRepository<OrganizationPlat, Integer> {
    public OrganizationPlat findById(Long id);
}
