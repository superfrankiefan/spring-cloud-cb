package com.frankie.demo.repository;

import com.frankie.demo.model.AbTestingRoute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbTestingRouteRepository extends CrudRepository<AbTestingRoute, String> {
    public AbTestingRoute findByServiceName(String serviceName);
}
