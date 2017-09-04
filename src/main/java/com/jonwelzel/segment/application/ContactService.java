package com.jonwelzel.segment.application;

import com.jonwelzel.segment.domain.models.Contact;
import com.jonwelzel.segment.infrastructure.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public void delete(long contactId) {
        contactRepository.delete(contactId);
    }

    public Contact save(Contact contact) {
        return contactRepository.saveAndFlush(contact);
    }

    public Contact update(Contact contact) {
        return contactRepository.saveAndFlush(contact);
    }
}
