package com.jbohorquez.emazon_hexagonal.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@Data
@Getter
@Setter
public class BrandRequest {

    @Column(length= NAME_MAX_LENGTH, nullable = false)
    private String name;

    @Column(name = "description", nullable = false, length = DESCRIPTION_BRAND_MAX_LENGTH)
    @NotBlank(message = "Brand description is mandatory")
    @Size(max = DESCRIPTION_BRAND_MAX_LENGTH)
    private String description;

    public BrandRequest(String newBrand, String newDescription) {
    }
}
