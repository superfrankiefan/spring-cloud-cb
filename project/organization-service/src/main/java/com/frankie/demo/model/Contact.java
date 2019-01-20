package com.frankie.demo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;

/**
 * Author: Frankie Fan
 * Created at: 2018.12.24
 * Description: Contact entity used to model the organization contact person
 */

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Email
    @Column(name = "email")
    private String email;

    @NumberFormat(pattern = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\\\D])|(18[0,5-9]))\\\\d{8}$")
    @Column(name = "phone")
    private String phone;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "contact")
    @JsonBackReference
    private Organization organization;

    public Contact() {
    }

    public Contact(String name, String email, String phone, Organization organization) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.organization = organization;
    }

    public Long getId() {
        return id;
    }

    public Contact withName(String name) {
        this.setName(name);
        return this;
    }

    public Contact withEmail(String email) {
        this.setEmail(email);
        return this;
    }

    public Contact withPhone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
