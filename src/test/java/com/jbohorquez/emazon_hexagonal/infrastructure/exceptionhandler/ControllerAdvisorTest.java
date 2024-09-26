package com.jbohorquez.emazon_hexagonal.infrastructure.exceptionhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerAdvisorTest {

    private ControllerAdvisor controllerAdvisor;

    @BeforeEach
    void setUp() {
        controllerAdvisor = new ControllerAdvisor();
    }

    @Test
    void handleRuntimeException_ShouldReturnInternalServerError() {
        // Arrange
        RuntimeException exception = new RuntimeException("Test Runtime Exception");

        // Act
        ResponseEntity<Map<String, String>> response = controllerAdvisor.handleRuntimeException(exception);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(ExceptionResponse.INTERNAL_ERROR.getMessage(), response.getBody().get(MESSAGE));
    }
}