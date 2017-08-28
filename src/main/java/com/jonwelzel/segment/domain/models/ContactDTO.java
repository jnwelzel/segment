package com.jonwelzel.segment.domain.models;

public class ContactDTO {
    public ContactDTO(long id, String name, String email, String jobTitle, String state, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.state = state;
        this.age = age;
    }

    private long id;

    private String name;

    private String email;

    private String jobTitle;

    private String state;

    private int age;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getState() {
        return state;
    }

    public int getAge() {
        return age;
    }
}
