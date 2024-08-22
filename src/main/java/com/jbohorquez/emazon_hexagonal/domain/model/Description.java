package com.jbohorquez.emazon_hexagonal.domain.model;

public class Description {

    private Long id;
    private String Description;

    public Description(Long id, String description) {
        this.id = id;
        Description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
