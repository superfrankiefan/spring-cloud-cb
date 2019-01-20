package com.frankie.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "licenses")
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "license_id", nullable = false)
    private Long licenseId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "license_type", nullable = false)
    private String licenseType;

    @Column(name = "license_max", nullable = false)
    private Integer licenseMax;

    @Column(name = "license_allocated", nullable = false)
    private Integer licenseAllocated;

    @Column(name = "comment")
    private String comment;

    @Column(name = "organization_id", nullable = false)
    private Long organizationId;

    @Transient
    private String organizationName = "";

    @Transient
    private String contactName = "";

    @Transient
    private String contactPhone = "";

    @Transient
    private String contactEmail = "";

    public License() {
    }

    public License(String productName, String licenseType,
                   int licenseMax, int licenseAllocated,
                   String comment) {
        this.productName = productName;
        this.licenseType = licenseType;
        this.licenseMax = licenseMax;
        this.licenseAllocated = licenseAllocated;
        this.comment = comment;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public License withId(Long id) {
        this.setLicenseId(id);
        return this;
    }

    public License withProductName(String productName) {
        this.setProductName(productName);
        return this;
    }

    public License withLicenseType(String licenseType) {
        this.setLicenseType(licenseType);
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public Integer getLicenseMax() {
        return licenseMax;
    }

    public void setLicenseMax(Integer licenseMax) {
        this.licenseMax = licenseMax;
    }

    public Long getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(Long licenseId) {
        this.licenseId = licenseId;
    }

    public Integer getLicenseAllocated() {
        return licenseAllocated;
    }

    public void setLicenseAllocated(Integer licenseAllocated) {
        this.licenseAllocated = licenseAllocated;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public License withComment(String comment){
        this.setComment(comment);
        return this;
    }

    public License withOrganizationName(String organizationName) {
        this.setOrganizationName(organizationName);
        return this;
    }

    public License withOrganizationId(Long organizationId) {
        this.setOrganizationId(organizationId);
        return this;
    }

    public License withContactName(String contactName) {
        this.setContactName(contactName);
        return this;
    }

    public License withContactPhone(String contactPhone) {
        this.setContactPhone(contactPhone);
        return this;
    }

    public License withContactEmail(String contactEmail) {
        this.setContactEmail(contactEmail);
        return this;
    }
}
