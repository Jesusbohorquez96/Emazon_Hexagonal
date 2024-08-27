package com.jbohorquez.emazon_hexagonal.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandResponse {

    private Long brandId;
    private String brandName;
    private String brandDescription;

    public BrandResponse(long l, String brand1, String description1) {
    }

}
