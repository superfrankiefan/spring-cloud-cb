package com.frankie.demo.service;

import com.frankie.demo.dao.ContactRepository;
import com.frankie.demo.dao.OrganizationRepository;
import com.frankie.demo.model.Contact;
import com.frankie.demo.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    ContactRepository contactRepository;

    public Long addOrg(String name, String contact_name, String email, String phone){
        Contact contact = new Contact().withName(contact_name).withEmail(email).withPhone(phone);
        Organization organization = new Organization().withName(name).withContact(contact);
        organizationRepository.save(organization);
        return organization.getId();
    }

    public Organization getOrg(Long organizationId) {
        Organization org = organizationRepository.findById(organizationId);
        return org;
    }

    public void updateOrg(Organization org){
        organizationRepository.save(org);
    }

    public void deleteOrg(Organization org){
        organizationRepository.delete(org);
    }


    public List<Organization> listAll(){
        List<Organization> orgs = new LinkedList<Organization>();
        for(Organization org: organizationRepository.findAll()){
            orgs.add(org);
        }
        return orgs;
    }
}
