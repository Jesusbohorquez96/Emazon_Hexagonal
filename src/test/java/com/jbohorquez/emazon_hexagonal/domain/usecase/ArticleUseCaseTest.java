package com.jbohorquez.emazon_hexagonal.domain.usecase;

import com.jbohorquez.emazon_hexagonal.domain.model.Article;
import com.jbohorquez.emazon_hexagonal.domain.spi.IArticlePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleUseCaseTest {

    private ArticleUseCase articleUseCase;
    private IArticlePersistencePort articlePersistencePort;

    @BeforeEach
    void setUp() {
        articlePersistencePort = mock(IArticlePersistencePort.class);
        articleUseCase = new ArticleUseCase(articlePersistencePort);
    }

    @Test
    void saveArticle() {
        Article article = new Article(1L, "ArticleName", "ArticleDescription", 10, 99.99, null);
        articleUseCase.saveArticle(article);
        verify(articlePersistencePort, times(1)).saveArticle(article);
    }

    @Test
    void getAllArticle() {
        List<Article> expectedArticles = Arrays.asList(
                new Article(1L, "ArticleName1", "Description1", 10, 99.99, null),
                new Article(2L, "ArticleName2", "Description2", 5, 49.99, null)
        );
        when(articlePersistencePort.getAllArticle()).thenReturn(expectedArticles);

        List<Article> actualArticles = articleUseCase.getAllArticle();
        assertEquals(expectedArticles, actualArticles);
        verify(articlePersistencePort, times(1)).getAllArticle();
    }

    @Test
    void getArticleById() {
        Article expectedArticle = new Article(1L, "ArticleName", "ArticleDescription", 10, 99.99, null);
        when(articlePersistencePort.getArticleById(1L)).thenReturn(expectedArticle);

        Article actualArticle = articleUseCase.getArticleById(1L);
        assertEquals(expectedArticle, actualArticle);
        verify(articlePersistencePort, times(1)).getArticleById(1L);
    }

    @Test
    void updateArticle() {
        Article article = new Article(1L, "UpdatedName", "UpdatedDescription", 8, 89.99, null);
        articleUseCase.updateArticle(article);
        verify(articlePersistencePort, times(1)).updateArticle(article);
    }

    @Test
    void deleteArticle() {
        Long articleId = 1L;
        articleUseCase.deleteArticle(articleId);
        verify(articlePersistencePort, times(1)).deleteArticle(articleId);
    }

    @Test
    void getArticlesWithPagination() {
        List<Article> articles = Arrays.asList(
                new Article(1L, "ArticleName1", "Description1", 10, 99.99, null),
                new Article(2L, "ArticleName2", "Description2", 5, 49.99, null)
        );
        Page<Article> expectedPage = new PageImpl<>(articles);

        when(articlePersistencePort.findAll(any(Pageable.class))).thenReturn(expectedPage);

        Page<Article> actualPage = articleUseCase.getArticles(0, 2, true);

        assertEquals(expectedPage, actualPage);

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(articlePersistencePort, times(1)).findAll(pageableCaptor.capture());

        Pageable pageable = pageableCaptor.getValue();
        assertEquals(PageRequest.of(0, 2, Sort.by("name").ascending()), pageable);
    }
}