package com.jonwelzel.segment.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Contact {

    public Contact() {}

    public Contact(String name, String email, String jobTitle, String state, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.state = state;
        this.age = age;
    }

    public Contact(long id, String name, String email, String jobTitle, String state, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.state = state;
        this.age = age;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String jobTitle;

    private String state;

    private int age;

    @JsonProperty("id")
    private long frontendId;

    public long getFrontendId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ContactDTO toDTO() {
        return new ContactDTO(this.id, this.name, this.email, this.jobTitle, this.state, this.age);
    }
}
