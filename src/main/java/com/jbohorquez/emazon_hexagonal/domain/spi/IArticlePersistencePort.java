package com.jbohorquez.emazon_hexagonal.domain.spi;

import com.jbohorquez.emazon_hexagonal.domain.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IArticlePersistencePort {

    void saveArticle(Article article);

    List<Article> getAllArticle();

    Article getArticleById(Long articleId);

    void updateArticle(Article article);

    void deleteArticle(Long articleId);

    Page<Article> getArticles(PageRequest pageRequest);

    Page<Article> getCategories(PageRequest pageRequest);

    Page<Article> findAll(Pageable pageable);
}
