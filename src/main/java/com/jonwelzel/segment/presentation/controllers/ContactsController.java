package com.jonwelzel.segment.presentation.controllers;

import com.jonwelzel.segment.application.ContactService;
import com.jonwelzel.segment.domain.models.Contact;
import com.jonwelzel.segment.infrastructure.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(method = RequestMethod.POST)
    public Contact add(@RequestBody Contact contact) {
        return contactService.save(contact);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> getContacts() {
        return contactService.findAll();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Contact update(@RequestBody Contact contact) {
        return contactService.update(contact);
    }

    @RequestMapping(value = "/{contactId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable long contactId) {
        contactService.delete(contactId);

        return ResponseEntity.ok("Contact deleted");
    }

}
