package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.ArticleResponse;
import com.jbohorquez.emazon_hexagonal.domain.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


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
    ArticleResponse toResponseList(Article article);

}
