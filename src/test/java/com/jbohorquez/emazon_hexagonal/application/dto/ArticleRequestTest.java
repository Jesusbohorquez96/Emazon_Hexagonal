package com.jbohorquez.emazon_hexagonal.application.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArticleRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidArticleRequest() {
        ArticleRequest request = new ArticleRequest();
        request.setName("Valid Name");
        request.setDescription("Valid description with less than 120 characters.");
        request.setStock(10);
        request.setPrice(100.50);
        request.setCategories(Set.of(1L, 2L));
        request.setBrand(1L);

        Set<ConstraintViolation<ArticleRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "There should be no validation errors for a valid ArticleRequest");
    }

    @Test
    void testNameNotBlank() {
        ArticleRequest request = new ArticleRequest();
        request.setName("");
        request.setDescription("Valid description");
        request.setStock(10);
        request.setPrice(100.50);
        request.setCategories(Set.of(1L, 2L));
        request.setBrand(1L);

        Set<ConstraintViolation<ArticleRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "There should be a validation error for blank name");
        assertEquals(1, violations.size());

        ConstraintViolation<ArticleRequest> violation = violations.iterator().next();
        assertEquals("Article name is mandatory", violation.getMessage()); // Ajusta según el mensaje real
    }

    @Test
    void testDescriptionNotBlank() {
        ArticleRequest request = new ArticleRequest();
        request.setName("Valid Name");
        request.setDescription("");
        request.setStock(10);
        request.setPrice(100.50);
        request.setCategories(Set.of(1L, 2L));
        request.setBrand(1L);

        Set<ConstraintViolation<ArticleRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "There should be a validation error for blank description");
        assertEquals(1, violations.size());

        ConstraintViolation<ArticleRequest> violation = violations.iterator().next();
        assertEquals("Article description is mandatory", violation.getMessage()); // Ajusta según el mensaje real
    }

    @Test
    void testStockNotNullAndNonNegative() {
        ArticleRequest request = new ArticleRequest();
        request.setName("Valid Name");
        request.setDescription("Valid description");
        request.setStock(-1); // Stock no puede ser negativo
        request.setPrice(100.50);
        request.setCategories(Set.of(1L, 2L));
        request.setBrand(1L);

        Set<ConstraintViolation<ArticleRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "There should be a validation error for negative stock");
        assertEquals(1, violations.size());

        ConstraintViolation<ArticleRequest> violation = violations.iterator().next();
        assertEquals("Stock cannot be negative", violation.getMessage()); // Ajusta según el mensaje real
    }

    @Test
    void testPriceNotNullAndValid() {
        ArticleRequest request = new ArticleRequest();
        request.setName("Valid Name");
        request.setDescription("Valid description");
        request.setStock(10);
        request.setPrice(-100.50); // Precio no puede ser negativo
        request.setCategories(Set.of(1L, 2L));
        request.setBrand(1L);

        Set<ConstraintViolation<ArticleRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "There should be a validation error for negative price");
        assertEquals(1, violations.size());

        ConstraintViolation<ArticleRequest> violation = violations.iterator().next();
        assertEquals("Price cannot be negative", violation.getMessage()); // Ajusta según el mensaje real
    }

    @Test
    void testBrandNotNull() {
        ArticleRequest request = new ArticleRequest();
        request.setName("Valid Name");
        request.setDescription("Valid description");
        request.setStock(10);
        request.setPrice(100.50);
        request.setCategories(Set.of(1L, 2L));
        request.setBrand(null); // La marca no puede ser nula

        Set<ConstraintViolation<ArticleRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "There should be a validation error for null brand");
        assertEquals(1, violations.size());

        ConstraintViolation<ArticleRequest> violation = violations.iterator().next();
        assertEquals("Brand ID is mandatory", violation.getMessage()); // Ajusta según el mensaje real
    }
}