package com.jbohorquez.emazon_hexagonal.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ArticleResponse {

    private Long articleId;
    private String articleName;
    private String articleDescription;
    private Integer articleStock;
    private Double articlePrice;
    private Set<ArticleCategoryResponse> articleCategories;
    private ArticleBrandResponse articleBrand;
}
