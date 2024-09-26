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
public class BrandRequest {

    @NotBlank(message = NAME_BLANK)
    @Size(max = NAME_MAX_LENGTH, message = NAME_REQUIRED)
    private String name;

    @NotBlank(message = DESCRIPTION_BLANK)
    @Size(max = DESCRIPTION_BRAND_MAX_LENGTH, message = DESCRIPTION_BRAND_REQUIRED)
    private String description;

    public BrandRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
