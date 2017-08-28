package com.jonwelzel.segment.domain.models;

import com.jonwelzel.segment.domain.enums.NumberOption;
import com.jonwelzel.segment.domain.enums.TextOption;

import java.util.List;

public class SegmentationDTO {

    public SegmentationDTO() {}

    public SegmentationDTO(long id, String segmentationName, TextOption nameOperator, String nameValue, TextOption emailOperator, String emailValue, NumberOption ageOperator, Integer ageValue, TextOption stateOperator, String stateValue, TextOption jobTitleOperator, String jobTitleValue, List<ContactDTO> contacts) {
        this.id = id;
        this.segmentationName = segmentationName;
        this.nameOperator = nameOperator;
        this.nameValue = nameValue;
        this.emailOperator = emailOperator;
        this.emailValue = emailValue;
        this.ageOperator = ageOperator;
        this.ageValue = ageValue;
        this.stateOperator = stateOperator;
        this.stateValue = stateValue;
        this.jobTitleOperator = jobTitleOperator;
        this.jobTitleValue = jobTitleValue;
        this.contacts = contacts;
    }

    private long id;

    private String segmentationName;

    private TextOption nameOperator;

    private String nameValue;

    private TextOption emailOperator;

    private String emailValue;

    private NumberOption ageOperator;

    private Integer ageValue;

    private TextOption stateOperator;

    private String stateValue;

    private TextOption jobTitleOperator;

    private String jobTitleValue;

    private List<ContactDTO> contacts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSegmentationName() {
        return segmentationName;
    }

    public void setSegmentationName(String segmentationName) {
        this.segmentationName = segmentationName;
    }

    public TextOption getNameOperator() {
        return nameOperator;
    }

    public void setNameOperator(TextOption nameOperator) {
        this.nameOperator = nameOperator;
    }

    public String getNameValue() {
        return nameValue;
    }

    public void setNameValue(String nameValue) {
        this.nameValue = nameValue;
    }

    public TextOption getEmailOperator() {
        return emailOperator;
    }

    public void setEmailOperator(TextOption emailOperator) {
        this.emailOperator = emailOperator;
    }

    public String getEmailValue() {
        return emailValue;
    }

    public void setEmailValue(String emailValue) {
        this.emailValue = emailValue;
    }

    public NumberOption getAgeOperator() {
        return ageOperator;
    }

    public void setAgeOperator(NumberOption ageOperator) {
        this.ageOperator = ageOperator;
    }

    public Integer getAgeValue() {
        return ageValue;
    }

    public void setAgeValue(Integer ageValue) {
        this.ageValue = ageValue;
    }

    public TextOption getStateOperator() {
        return stateOperator;
    }

    public void setStateOperator(TextOption stateOperator) {
        this.stateOperator = stateOperator;
    }

    public String getStateValue() {
        return stateValue;
    }

    public void setStateValue(String stateValue) {
        this.stateValue = stateValue;
    }

    public TextOption getJobTitleOperator() {
        return jobTitleOperator;
    }

    public void setJobTitleOperator(TextOption jobTitleOperator) {
        this.jobTitleOperator = jobTitleOperator;
    }

    public String getJobTitleValue() {
        return jobTitleValue;
    }

    public void setJobTitleValue(String jobTitleValue) {
        this.jobTitleValue = jobTitleValue;
    }

    public List<ContactDTO> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactDTO> contacts) {
        this.contacts = contacts;
    }
}
