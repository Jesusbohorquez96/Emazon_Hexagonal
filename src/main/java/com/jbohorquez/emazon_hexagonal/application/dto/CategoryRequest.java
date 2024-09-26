package com.jbohorquez.emazon_hexagonal.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@Data
@Getter
@Setter
public class CategoryRequest {

    @NotBlank(message = NAME_BLANK)
    @Size(max = NAME_MAX_LENGTH, message = NAME_REQUIRED)
    private String name;

    @NotBlank(message = DESCRIPTION_BLANK)
    @Size(max = DESCRIPTION_MAX_LENGTH, message = DESCRIPTION_REQUIRED)
    private String description;

    public CategoryRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
