package com.frankie.demo.controller;

import com.frankie.demo.model.License;
import com.frankie.demo.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {
    @Autowired
    private LicenseService licenseService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<License> getLicenses(@PathVariable("organizationId") Long organizationId) {

        return licenseService.getLicensesByOrg(organizationId);
    }

    @RequestMapping(value = "/{licenseId}", method = RequestMethod.GET)
    public License getLicenses(@PathVariable("organizationId") Long organizationId,
                               @PathVariable("licenseId") Long licenseId) {

        return licenseService.getLicense(organizationId, licenseId, "rest");
    }

    @RequestMapping(value = "/{licenseId}/{clientType}", method = RequestMethod.GET)
    public License getLicensesWithClient(@PathVariable("organizationId") Long organizationId,
                                         @PathVariable("licenseId") Long licenseId,
                                         @PathVariable("clientType") String clientType) {

        return licenseService.getLicense(organizationId, licenseId, clientType);
    }

    @RequestMapping(value = "{licenseId}", method = RequestMethod.PUT)
    public void updateLicenses(@PathVariable("licenseId") String licenseId, @RequestBody License license) {
        licenseService.updateLicense(license);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void saveLicenses(@RequestBody License license) {
        licenseService.saveLicense(license);
    }

    @RequestMapping(value = "{licenseId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLicenses(@PathVariable("licenseId") String licenseId, @RequestBody License license) {
        licenseService.deleteLicense(license);
    }
}
