package com.jbohorquez.emazon_hexagonal.domain.api;

import com.jbohorquez.emazon_hexagonal.domain.model.Article;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IArticleServicePort {

    void saveArticle(Article article);

    List<Article> getAllArticle();

    Article getArticleById(Long articleId);

    void updateArticle(Article article);

    void deleteArticle(Long articleId);

    Page<Article> getArticles(int page, int size, String sorBy, boolean ascending);

    Page<Article> getArticles(int page, int size, boolean ascending);

    Page<Article> getArticlesFilter(int page, int size, String value, boolean ascending, List<Long> articleIds, String categoryName, String brandName);
}

