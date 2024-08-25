package com.jbohorquez.emazon_hexagonal.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandResponse {

    private Long brandId;
    private String brandName;
    private String brandDescription;
    //TODO: agregar validaciones
}
