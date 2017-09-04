package com.jonwelzel.segment.application;

import com.jonwelzel.segment.domain.enums.NumberOption;
import com.jonwelzel.segment.domain.enums.TextOption;
import com.jonwelzel.segment.domain.models.Contact;
import com.jonwelzel.segment.domain.models.Segmentation;
import com.jonwelzel.segment.domain.models.SegmentationDTO;
import com.jonwelzel.segment.infrastructure.ContactRepository;
import com.jonwelzel.segment.infrastructure.SegmentationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SegmentationServiceTests {

    @Autowired
    SegmentationService segmentationService;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    SegmentationRepository segmentationRepository;

    @Test
    public void shouldFindASegmentationAndItsCorrespondingContactsUsingAllFieldsInSegmentation() {
        Contact contact = new Contact("Jonathan Welzel", "jnwelzel@gmail.com", "Fullstack developer", "SC", 30);
        contact = contactRepository.save(contact);
        Segmentation allFieldsSegmentation = new Segmentation("All fields segmentation", TextOption.EQUALS_TO, "Jonathan Welzel", TextOption.EQUALS_TO, "jnwelzel@gmail.com", NumberOption.EQUALS_TO, 30, TextOption.EQUALS_TO, "SC", TextOption.EQUALS_TO, "Fullstack developer", null);
        allFieldsSegmentation = segmentationRepository.save(allFieldsSegmentation);

        SegmentationDTO result = segmentationService.getSegmentationWithContacts(allFieldsSegmentation.getFrontendId());

        assertThat(result).isNotNull();
        assertThat(result.getSegmentationName()).isEqualTo(allFieldsSegmentation.getSegmentationName());
        assertThat(result.getContacts().get(0).getName()).isEqualTo(contact.getName());
        assertThat(result.getContacts().get(0).getAge()).isEqualTo(contact.getAge());
        assertThat(result.getContacts().get(0).getEmail()).isEqualTo(contact.getEmail());
        assertThat(result.getContacts().get(0).getState()).isEqualTo(contact.getState());
        assertThat(result.getContacts().get(0).getJobTitle()).isEqualTo(contact.getJobTitle());
    }

}
