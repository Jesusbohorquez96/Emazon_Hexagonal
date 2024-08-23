package com.jbohorquez.emazon_hexagonal.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BrandRequest {

    private String name;
    private String description;
}
