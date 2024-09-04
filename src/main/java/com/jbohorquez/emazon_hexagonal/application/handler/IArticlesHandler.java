package com.jbohorquez.emazon_hexagonal.application.handler;

import com.jbohorquez.emazon_hexagonal.application.dto.ArticleRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.ArticleResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IArticlesHandler {

    Page<ArticleResponse> getCategories(int page, int size, String sortDirection);

    Page<ArticleResponse> getBrands(int page, int size, String sortDirection);

    Page<ArticleResponse> getArticle(int page, int size, String sortDirection);

    void saveArticleIn(ArticleRequest articleRequest);

    Page<ArticleResponse> getArticle(int page, int size, String sortBy, String sortDirection);

    List<ArticleResponse> getArticleFrom();

    ArticleResponse getArticleFrom(Long articleId);

    void updateArticleIn(ArticleRequest articleRequest);

    void deleteArticleFrom(Long articleId);
}
