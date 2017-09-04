package com.jonwelzel.segment.presentation.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonwelzel.segment.application.ContactService;
import com.jonwelzel.segment.domain.models.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ContactsControllerTests {

    @MockBean
    private ContactService contactService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Contact sampleContact = new Contact("Jonathan Welzel", "jnwelzel@gmail.com", "Fullstack developer", "SC", 30);
    private final Contact sampleDBContact = new Contact(1L,"Jonathan Welzel", "jnwelzel@gmail.com", "Fullstack developer", "SC", 30);
    private final String sampleContactString = "{\"name\":\"Jonathan Welzel\",\"email\":\"jnwelzel@gmail.com\",\"jobTitle\":\"Fullstack developer\",\"state\":\"SC\",\"age\":30,\"id\":1}";
    private final String sampleContactsListString = "[{\"name\":\"Jonathan Welzel\",\"email\":\"jnwelzel@gmail.com\",\"jobTitle\":\"Fullstack developer\",\"state\":\"SC\",\"age\":30,\"id\":1}]";
    private final List<Contact> sampleContactsList = new ArrayList<>(Arrays.asList(sampleDBContact));

    @Before
    public void setup() {
        given(contactService.save(any())).willReturn(sampleDBContact);
        given(contactService.update(any())).willReturn(sampleDBContact);
        given(contactService.findAll()).willReturn(sampleContactsList);
    }

    @Test
    public void createNewContact() throws Exception {
        this.mockMvc.perform(post("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleContact)))
                .andExpect(status().isOk())
                .andExpect(content().string(sampleContactString));
    }

    @Test
    public void getContactsList() throws Exception {
        this.mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(content().json(sampleContactsListString));
    }

    @Test
    public void updateContact() throws Exception {
        this.mockMvc.perform(put("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleContact)))
                .andExpect(status().isOk())
                .andExpect(content().string(sampleContactString));
    }

    @Test
    public void deleteContact() throws Exception {
        this.mockMvc.perform(delete("/contacts/1"))
                .andExpect(status().isOk());
    }
}
