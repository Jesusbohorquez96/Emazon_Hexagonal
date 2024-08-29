package com.jbohorquez.emazon_hexagonal.domain.usecase;

import com.jbohorquez.emazon_hexagonal.domain.api.IArticleServicePort;
import com.jbohorquez.emazon_hexagonal.domain.model.Article;
import com.jbohorquez.emazon_hexagonal.domain.spi.IArticlePersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;

public  class ArticleUseCase implements IArticleServicePort {

    private final IArticlePersistencePort articlePersistencePort;

    public ArticleUseCase(IArticlePersistencePort articlePersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
    }

    @Override
    public void saveArticle(Article article) {
      articlePersistencePort.saveArticle(article);
    }

    @Override
    public List<Article> getAllArticle() {
        return articlePersistencePort.getAllArticle();
    }

    @Override
    public Article getArticleById(Long articleId) {
        return articlePersistencePort.getArticleById(articleId);
    }

    @Override
    public void updateArticle(Article article) {
        articlePersistencePort.updateArticle(article);
    }

    @Override
    public void deleteArticle(Long articleId) {
        articlePersistencePort.deleteArticle(articleId);
    }

    @Override
    public Page<Article> getArticles(int page, int size, boolean ascending) {
        Sort sort = Sort.by("name");
        sort = ascending ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return articlePersistencePort.findAll(pageable);
    }

}
