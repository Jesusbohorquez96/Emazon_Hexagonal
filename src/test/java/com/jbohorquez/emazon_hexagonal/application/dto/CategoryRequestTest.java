package com.jbohorquez.emazon_hexagonal.application.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CategoryRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidCategoryRequest() {
        CategoryRequest request = new CategoryRequest("Electronics", "All kinds of electronic items");

        Set<ConstraintViolation<CategoryRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "There should be no validation errors");
    }

    @Test
    void testCategoryNameNotBlank() {
        CategoryRequest request = new CategoryRequest("", "All kinds of electronic items");

        Set<ConstraintViolation<CategoryRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "There should be a validation error for blank name");
        assertEquals(1, violations.size());

        ConstraintViolation<CategoryRequest> violation = violations.iterator().next();
        assertEquals("Name cannot be blank", violation.getMessage());
    }

    @Test
    void testCategoryDescriptionNotBlank() {
        CategoryRequest request = new CategoryRequest("Electronics", "");

        Set<ConstraintViolation<CategoryRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "There should be a validation error for blank description");
        assertEquals(1, violations.size());

        ConstraintViolation<CategoryRequest> violation = violations.iterator().next();
        assertEquals("Description cannot be blank", violation.getMessage());
    }
}