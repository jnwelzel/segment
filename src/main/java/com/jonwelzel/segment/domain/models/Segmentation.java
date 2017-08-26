package com.jonwelzel.segment.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jonwelzel.segment.domain.enums.NumberOption;
import com.jonwelzel.segment.domain.enums.TextOption;

import javax.persistence.*;

@Entity
public class Segmentation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String segmentationName;

    @Enumerated(EnumType.STRING)
    private TextOption nameOperator;

    private String nameValue;

    @Enumerated(EnumType.STRING)
    private TextOption emailOperator;

    private String emailValue;

    @Enumerated(EnumType.STRING)
    private NumberOption ageOperator;

    private Integer ageValue;

    @Enumerated(EnumType.STRING)
    private TextOption stateOperator;

    private String stateValue;

    @Enumerated(EnumType.STRING)
    private TextOption jobTitleOperator;

    private String jobTitleValue;

    @JsonProperty("id")
    private long frontendId;

    public long getFrontendId() {
        return id;
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
}
