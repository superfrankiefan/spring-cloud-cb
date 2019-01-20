package com.frankie.demo.repository;

import com.frankie.demo.model.OrganizationNew;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationNewRepository extends CrudRepository<OrganizationNew, Integer> {
    public OrganizationNew findById(Long id);
}
