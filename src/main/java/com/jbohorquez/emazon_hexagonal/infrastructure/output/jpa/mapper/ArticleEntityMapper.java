package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper;

import com.jbohorquez.emazon_hexagonal.domain.model.Article;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ArticleEntityMapper {

    ArticleEntity toEntity(Article article);

    Article toArticle(ArticleEntity articleEntity);

    List<Article> toArticleList(List<ArticleEntity> articleEntityList);
}
