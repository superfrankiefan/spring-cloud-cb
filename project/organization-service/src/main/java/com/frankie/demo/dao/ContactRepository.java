package com.frankie.demo.dao;

import com.frankie.demo.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    public Contact findById(Long id);

}
