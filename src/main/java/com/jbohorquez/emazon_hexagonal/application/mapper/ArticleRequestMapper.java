package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.ArticleRequest;
import com.jbohorquez.emazon_hexagonal.domain.model.Article;
import com.jbohorquez.emazon_hexagonal.domain.model.Brand;
import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ArticleRequestMapper {

    Article toArticle(ArticleRequest articleRequest);

    default Set<Category> Categories(Set<Long> categories) {
        return categories.stream().map(
                (categoryId) -> {
                    Category category = new Category();
                    category.setId(categoryId);
                    return category;
                }

        ).collect(Collectors.toSet());
    }

    default Brand map(Long brandId) {
        if (brandId == null) {
            return null;
        }
        Brand brand = new Brand();
        brand.setId(brandId);
        return brand;
    }

}