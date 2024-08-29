package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.ArticleRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.BrandRequest;
import com.jbohorquez.emazon_hexagonal.domain.model.Article;
import com.jbohorquez.emazon_hexagonal.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ArticleRequestMapper {

    Brand toBrand(BrandRequest brandRequest);

    Article toArticle(ArticleRequest articleRequest);
}