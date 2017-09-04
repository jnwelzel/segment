package com.jonwelzel.segment.presentation.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonwelzel.segment.application.SegmentationService;
import com.jonwelzel.segment.domain.enums.NumberOption;
import com.jonwelzel.segment.domain.enums.TextOption;
import com.jonwelzel.segment.domain.models.Contact;
import com.jonwelzel.segment.domain.models.ContactDTO;
import com.jonwelzel.segment.domain.models.Segmentation;
import com.jonwelzel.segment.domain.models.SegmentationDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SegmentationControllerTests {

    @MockBean
    private SegmentationService segmentationService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Segmentation sampleSegmentation = new Segmentation(1L, "Sample segmentation",TextOption.EQUALS_TO, "Jonathan Welzel",TextOption.EQUALS_TO, "jnwelzel@gmail.com",NumberOption.EQUALS_TO, 30,TextOption.EQUALS_TO, "SC",TextOption.EQUALS_TO, "Fullstack developer", null);
    private final Segmentation sampleNewSegmentation = new Segmentation("Sample segmentation",TextOption.EQUALS_TO, "Jonathan Welzel",TextOption.EQUALS_TO, "jnwelzel@gmail.com",NumberOption.EQUALS_TO, 30,TextOption.EQUALS_TO, "SC",TextOption.EQUALS_TO, "Fullstack developer", null);
    private final String sampleSegmentationString = "{\"id\":1,\"segmentationName\":\"Sample segmentation\",\"nameOperator\":\"EQUALS_TO\",\"nameValue\":\"Jonathan Welzel\",\"emailOperator\":\"EQUALS_TO\",\"emailValue\":\"jnwelzel@gmail.com\",\"ageOperator\":\"EQUALS_TO\",\"ageValue\":30,\"stateOperator\":\"EQUALS_TO\",\"stateValue\":\"SC\",\"jobTitleOperator\":\"EQUALS_TO\",\"jobTitleValue\":\"Fullstack developer\",\"contacts\":null}";
    private final String sampleSegmentationsListString = "[{\"id\":1,\"segmentationName\":\"Sample segmentation\",\"nameOperator\":\"EQUALS_TO\",\"nameValue\":\"Jonathan Welzel\",\"emailOperator\":\"EQUALS_TO\",\"emailValue\":\"jnwelzel@gmail.com\",\"ageOperator\":\"EQUALS_TO\",\"ageValue\":30,\"stateOperator\":\"EQUALS_TO\",\"stateValue\":\"SC\",\"jobTitleOperator\":\"EQUALS_TO\",\"jobTitleValue\":\"Fullstack developer\",\"contacts\":null}]";
    private final List<Segmentation> sampleSegmentationsList = new ArrayList<>(Arrays.asList(sampleSegmentation));
    private final ContactDTO sampleDBContact = new ContactDTO(1L,"Jonathan Welzel", "jnwelzel@gmail.com", "Fullstack developer", "SC", 30);
    private final SegmentationDTO sampleSegmentationWithContact = new SegmentationDTO(1L, "Sample segmentation",TextOption.EQUALS_TO, "Jonathan Welzel",TextOption.EQUALS_TO, "jnwelzel@gmail.com",NumberOption.EQUALS_TO, 30,TextOption.EQUALS_TO, "SC",TextOption.EQUALS_TO, "Fullstack developer", Arrays.asList(sampleDBContact));
    private final String sampleSegmentationWithContactString = "{\"id\":1,\"segmentationName\":\"Sample segmentation\",\"nameOperator\":\"EQUALS_TO\",\"nameValue\":\"Jonathan Welzel\",\"emailOperator\":\"EQUALS_TO\",\"emailValue\":\"jnwelzel@gmail.com\",\"ageOperator\":\"EQUALS_TO\",\"ageValue\":30,\"stateOperator\":\"EQUALS_TO\",\"stateValue\":\"SC\",\"jobTitleOperator\":\"EQUALS_TO\",\"jobTitleValue\":\"Fullstack developer\",\"contacts\":[{\"id\":1,\"name\":\"Jonathan Welzel\",\"email\":\"jnwelzel@gmail.com\",\"jobTitle\":\"Fullstack developer\",\"state\":\"SC\",\"age\":30}]}";

    @Before
    public void setup() {
        given(segmentationService .save(any())).willReturn(sampleSegmentation);
        given(segmentationService.findAll()).willReturn(sampleSegmentationsList);
        given(segmentationService.getSegmentationWithContacts(1L)).willReturn(sampleSegmentationWithContact);
    }

    @Test
    public void createNewSegmentation() throws Exception {
        this.mockMvc.perform(post("/segmentations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleNewSegmentation)))
                .andExpect(status().isOk())
                .andExpect(content().string(sampleSegmentationString));
    }

    @Test
    public void findAllSegmentations() throws Exception {
        this.mockMvc.perform(get("/segmentations"))
                .andExpect(status().isOk())
                .andExpect(content().json(sampleSegmentationsListString));
    }

    @Test
    public void findOneSegmentation() throws Exception {
        this.mockMvc.perform(get("/segmentations/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(sampleSegmentationWithContactString));
    }

    @Test
    public void updateSegmentation() throws Exception {
        this.mockMvc.perform(put("/segmentations/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleNewSegmentation)))
                .andExpect(status().isOk())
                .andExpect(content().string(sampleSegmentationString));
    }
}
