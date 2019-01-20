package com.frankie.demo.filters;

import com.frankie.demo.model.AbTestingRoute;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

/*
 * Dynamic route between two organization services
 */
@Component
public class SpecialRoutesFilter extends ZuulFilter {
    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;

    @Autowired
    FilterUtils filterUtils;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String filterType() {
        return filterUtils.ROUTE_FILTER_TYPE;
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    private AbTestingRoute getAbRoutingInfo(String serviceName) {
        ResponseEntity<AbTestingRoute> restExchange = null;
        try {
            restExchange = restTemplate.exchange(
                    "http://specialroutesservice/v1/route/abtesting/{serviceName}",
                    HttpMethod.GET,
                    null, AbTestingRoute.class, serviceName);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) return null;
            throw ex;
        }
        return restExchange.getBody();
    }

    public boolean useSpecialRoute(AbTestingRoute testRoute) {
        Random random = new Random();

        if (testRoute.getActive().equals("N")) return false;

        int value = random.nextInt((10 - 1) + 1) + 1;

        if (testRoute.getWeight() < value) return true;

        return false;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        AbTestingRoute abTestRoute = getAbRoutingInfo(filterUtils.getServiceId());

        if (abTestRoute != null && useSpecialRoute(abTestRoute)) {
            List<ServiceInstance> instances = discoveryClient.getInstances("specialroutesservice");
            try {
                if (instances != null && instances.size() > 0) {
                    //String serviceUri = String.format("%s/v1/organizationsplat/1", instances.get(0).getUri().toString());
                    ctx.setRouteHost(instances.get(0).getUri().toURL());
                } else {
                    throw new IllegalStateException("Target service instance not found!");
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Couldn't get service URL!", e);
            }
        }
        return null;
    }
}
