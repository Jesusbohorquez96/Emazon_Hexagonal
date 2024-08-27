package com.jbohorquez.emazon_hexagonal.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.DESCRIPTION_MAX_LENGTH;
import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.NAME_MAX_LENGTH;

@Getter
@Setter
public class CategoryRequest {

    //Not null max NAME_MAX_LENGTH characters
    @Column(length= NAME_MAX_LENGTH, nullable = false)
    private String name;

    //Not null max DESCRIPTION_MAX_LENGTH characters
    @Column(length = DESCRIPTION_MAX_LENGTH, nullable = false)
    private String description;

    public CategoryRequest(String newCategory, String newDescription) {
    }
}
