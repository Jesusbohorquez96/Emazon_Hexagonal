package com.jbohorquez.emazon_hexagonal.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {

    private Long categoryId;
    private String categoryName;
    private String categoryDescription;

    public CategoryResponse(long l, String category1, String description1) {
    }
}
