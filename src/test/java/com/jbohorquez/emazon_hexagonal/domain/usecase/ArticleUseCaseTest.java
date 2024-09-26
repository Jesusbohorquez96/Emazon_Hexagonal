package com.jbohorquez.emazon_hexagonal.domain.usecase;

import com.jbohorquez.emazon_hexagonal.domain.model.Article;
import com.jbohorquez.emazon_hexagonal.domain.spi.IArticlePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ArticleUseCaseTest {

    @Mock
    private IArticlePersistencePort articlePersistencePort;

    @InjectMocks
    private ArticleUseCase articleUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveArticle() {
        // Arrange
        Article article = new Article();  // Crea un objeto de prueba de tipo Article

        // Act
        articleUseCase.saveArticle(article);

        // Assert
        verify(articlePersistencePort, times(1)).saveArticle(article);
    }

    @Test
    public void testGetAllArticle() {
        // Arrange
        List<Article> articles = Collections.singletonList(new Article());
        when(articlePersistencePort.getAllArticle()).thenReturn(articles);

        // Act
        List<Article> result = articleUseCase.getAllArticle();

        // Assert
        assertEquals(1, result.size());
        verify(articlePersistencePort, times(1)).getAllArticle();
    }

    @Test
    public void testGetArticleById() {
        // Arrange
        Long articleId = 1L;
        Article article = new Article();
        when(articlePersistencePort.getArticleById(articleId)).thenReturn(article);

        // Act
        Article result = articleUseCase.getArticleById(articleId);

        // Assert
        assertNotNull(result);
        verify(articlePersistencePort, times(1)).getArticleById(articleId);
    }

    @Test
    public void testUpdateArticle() {
        // Arrange
        Article article = new Article();

        // Act
        articleUseCase.updateArticle(article);

        // Assert
        verify(articlePersistencePort, times(1)).updateArticle(article);
    }

    @Test
    public void testDeleteArticle() {
        // Arrange
        Long articleId = 1L;

        // Act
        articleUseCase.deleteArticle(articleId);

        // Assert
        verify(articlePersistencePort, times(1)).deleteArticle(articleId);
    }

    @Test
    public void testGetArticlesWithSortBy() {
        // Arrange
        Page<Article> articles = new PageImpl<>(Collections.singletonList(new Article()));
        when(articlePersistencePort.findAll(any(Pageable.class))).thenReturn(articles);

        // Act
        Page<Article> result = articleUseCase.getArticles(0, 10, "name", true);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(articlePersistencePort, times(1)).findAll(any(Pageable.class));
    }

    @Test
    public void testGetArticlesWithoutSortBy() {
        // Arrange
        Page<Article> articles = new PageImpl<>(Collections.singletonList(new Article()));
        when(articlePersistencePort.findAll(any(Pageable.class))).thenReturn(articles);

        // Act
        Page<Article> result = articleUseCase.getArticles(0, 10, true);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(articlePersistencePort, times(1)).findAll(any(Pageable.class));
    }
}