package com.jbohorquez.emazon_hexagonal.application.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BrandRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidBrandRequest() {
        BrandRequest request = new BrandRequest("Nike", "Sportswear and footwear brand");

        Set<ConstraintViolation<BrandRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "There should be no validation errors for a valid BrandRequest");
    }

    @Test
    void testBrandNameNotBlank() {
        BrandRequest request = new BrandRequest("", "Sportswear and footwear brand");

        Set<ConstraintViolation<BrandRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "There should be a validation error for blank brand name");
        assertEquals(1, violations.size());

        ConstraintViolation<BrandRequest> violation = violations.iterator().next();
        assertEquals("Name cannot be blank", violation.getMessage());
    }

    @Test
    void testBrandDescriptionNotBlank() {
        BrandRequest request = new BrandRequest("Nike", "");

        Set<ConstraintViolation<BrandRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "There should be a validation error for blank brand description");
        assertEquals(1, violations.size());

        ConstraintViolation<BrandRequest> violation = violations.iterator().next();
        assertEquals("Description cannot be blank", violation.getMessage());
    }
}