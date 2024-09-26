package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.ArticleBrandResponse;
import com.jbohorquez.emazon_hexagonal.application.dto.ArticleCategoryResponse;
import com.jbohorquez.emazon_hexagonal.application.dto.ArticleResponse;
import com.jbohorquez.emazon_hexagonal.domain.model.Article;
import com.jbohorquez.emazon_hexagonal.domain.model.Brand;
import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArticleResponseMapperTest {

    private ArticleResponseMapper articleResponseMapper;

    @BeforeEach
    public void setUp() {
        articleResponseMapper = Mappers.getMapper(ArticleResponseMapper.class);
    }

    @Test
    public void testMapCategoriesToResponsesWithValidSet() {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Test Category");

        Set<Category> categories = Collections.singleton(category);

        // Act
        Set<ArticleCategoryResponse> responses = articleResponseMapper.mapCategoriesToResponses(categories);

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        ArticleCategoryResponse response = responses.iterator().next();
        assertEquals(category.getId(), response.getCategoryId());
        assertEquals(category.getName(), response.getCategoryName());
    }

    @Test
    public void testMapBrandToResponse() {
        // Arrange
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("Test Brand");

        // Act
        ArticleBrandResponse response = articleResponseMapper.map(brand);

        // Assert
        assertNotNull(response);
        assertEquals(brand.getId(), response.getBrandId());
        assertEquals(brand.getName(), response.getBrandName());
    }

    @Test
    public void testMapNullBrand() {
        // Act
        ArticleBrandResponse response = articleResponseMapper.map(null);

        // Assert
        assertNull(response);
    }

    @Test
    public void testToBrandResponseListWithNullArticle() {
        // Act
        ArticleBrandResponse response = articleResponseMapper.toBrandResponseList(null);

        // Assert
        assertNull(response);
    }

    @Test
    public void testToResponseListWithValidArticle() {
        // Arrange
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("Test Brand");

        Category category = new Category();
        category.setId(1L);
        category.setName("Test Category");

        Article article = new Article();
        article.setId(1L);
        article.setName("Test Article");
        article.setDescription("Test Description");
        article.setStock(100);
        article.setPrice(50.0);
        article.setBrand(brand);
        article.setCategories(Collections.singleton(category));

        // Act
        ArticleResponse response = articleResponseMapper.toResponseList(article);

        // Assert
        assertNotNull(response);
        assertEquals(article.getId(), response.getArticleId());
        assertEquals(article.getName(), response.getArticleName());
        assertEquals(article.getDescription(), response.getArticleDescription());
        assertEquals(article.getStock(), response.getArticleStock());
        assertEquals(article.getPrice(), response.getArticlePrice());

        // Verificar la marca
        assertNotNull(response.getArticleBrand());
        assertEquals(brand.getId(), response.getArticleBrand().getBrandId());
        assertEquals(brand.getName(), response.getArticleBrand().getBrandName());

        // Verificar las categorías
        assertNotNull(response.getArticleCategories());
        assertEquals(1, response.getArticleCategories().size());
        ArticleCategoryResponse categoryResponse = response.getArticleCategories().iterator().next();
        assertEquals(category.getId(), categoryResponse.getCategoryId());
        assertEquals(category.getName(), categoryResponse.getCategoryName());
    }

    @Test
    public void testToResponseListWithNullArticle() {
        // Act
        ArticleResponse response = articleResponseMapper.toResponseList(null);

        // Assert
        assertNull(response);
    }
}