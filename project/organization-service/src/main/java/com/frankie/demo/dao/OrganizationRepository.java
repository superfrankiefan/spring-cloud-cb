package com.frankie.demo.dao;

import com.frankie.demo.model.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Integer> {
    public Organization findById(Long id);

}
