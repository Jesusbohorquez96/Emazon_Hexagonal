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

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@Mapper(componentModel = SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {Article.class})
public interface ArticleResponseMapper {

    @Mapping(target = ARTICLE_ID, source = ID)
    @Mapping(target = ARTICLE_NAME, source = NAME)
    @Mapping(target = ARTICLE_DESCRIPTION, source = DESCRIPTION)
    @Mapping(target = ARTICLE_STOCK, source = STOCK)
    @Mapping(target = ARTICLE_PRICE, source = PRICE)
    @Mapping(target = ARTICLE_BRAND, source = BRAND)
    @Mapping(target = ARTICLE_CATEGORIES, source = CATEGORIES)
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

    ArticleBrandResponse toBrandResponseList(Article article);
}
