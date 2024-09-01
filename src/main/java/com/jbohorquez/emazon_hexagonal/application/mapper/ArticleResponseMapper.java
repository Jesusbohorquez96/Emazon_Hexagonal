package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.ArticleBrandResponse;
import com.jbohorquez.emazon_hexagonal.application.dto.ArticleCategoryResponse;
import com.jbohorquez.emazon_hexagonal.application.dto.ArticleResponse;
import com.jbohorquez.emazon_hexagonal.domain.model.Article;
import com.jbohorquez.emazon_hexagonal.domain.model.Brand;
import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {Article.class})
public interface ArticleResponseMapper {

    @Mapping(target = "articleId", source = "id")
    @Mapping(target = "articleName", source = "name")
    @Mapping(target = "articleDescription", source = "description")
    @Mapping(target = "articleStock", source = "stock")
    @Mapping(target = "articlePrice", source = "price")
    @Mapping(target = "articleBrand", source = "brand")
    @Mapping(target = "articleCategories", source = "categories")
    ArticleResponse toResponseList(Article article);

    default Set<ArticleCategoryResponse> mapCategoriesToResponses(Set<Category> categories) {
        return categories.stream().map(
                category -> {
                    ArticleCategoryResponse response = new ArticleCategoryResponse();
                    response.setCategoryId(category.getId());
                    response.setCategoryName(category.getName());
                    return response;
                }
        ).collect(Collectors.toSet());
    }
    default ArticleBrandResponse map(Brand brand) {
        if (brand == null) {
            return null;
        }
        ArticleBrandResponse response = new ArticleBrandResponse();
        response.setBrandId(brand.getId());
        response.setBrandName(brand.getName());
        return response;
    }
}
