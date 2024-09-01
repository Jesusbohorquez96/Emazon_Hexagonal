package com.jbohorquez.emazon_hexagonal.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.DESCRIPTION_MAX_LENGTH;
import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.NAME_MAX_LENGTH;

@Data
@Getter
@Setter
public class CategoryRequest {

    @NotBlank(message = "Name cannot be blank")
    @Size(max = NAME_MAX_LENGTH, message = "The name must not exceed " + NAME_MAX_LENGTH + " characters")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = DESCRIPTION_MAX_LENGTH, message = "The description must not exceed " + DESCRIPTION_MAX_LENGTH + " characters")
    private String description;

    public CategoryRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
