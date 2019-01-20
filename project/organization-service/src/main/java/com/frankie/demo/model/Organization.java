package com.frankie.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "organization")
public class Organization {
    @Id
    @Column(name = "org_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "org_name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact")
    @JsonManagedReference
    private Contact contact;

    public Organization(String name, Contact contact) {
        this.name = name;
        this.contact = contact;
    }

    public Organization() {
    }

    public Organization withName(String name){
        this.setName(name);
        return this;
    }

    public Organization withContact(Contact contact){
        this.setContact(contact);
        return this;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contact=" + contact +
                '}';
    }
}
