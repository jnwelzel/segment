package com.jonwelzel.segment.presentation.controllers;

import com.jonwelzel.segment.domain.models.Contact;
import com.jonwelzel.segment.infrastructure.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Contact add(@RequestBody Contact contact) {
        return contactRepository.saveAndFlush(contact);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Contact update(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

}
