package com.jbohorquez.emazon_hexagonal.application.dto;

import com.jbohorquez.emazon_hexagonal.domain.model.Category;
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
    private Set<Category> articleCategories;

    public ArticleResponse(Long articleId, String articleName, String articleDescription, Integer articleStock, Double articlePrice, Set<Category> articleCategories) {
        this.articleId = articleId;
        this.articleName = articleName;
        this.articleDescription = articleDescription;
        this.articleStock = articleStock;
        this.articlePrice = articlePrice;
        this.articleCategories = articleCategories;
    }
}
