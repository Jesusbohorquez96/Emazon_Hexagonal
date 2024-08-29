package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.ArticleRequest;
import com.jbohorquez.emazon_hexagonal.domain.model.Article;
import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ArticleRequestMapper {

    Article toArticle(ArticleRequest articleRequest);

    //convertir de ArticleRequest.categories set<Long> a Article.categories set<Category>
    default Set<Category> kokiotoCategories(Set<Long> categories) {
        return categories.stream().map(
                (categoryId) -> {
                    Category category = new Category();
                    category.setId(categoryId);
                    return category;
                }

        ).collect(Collectors.toSet());
    }

}