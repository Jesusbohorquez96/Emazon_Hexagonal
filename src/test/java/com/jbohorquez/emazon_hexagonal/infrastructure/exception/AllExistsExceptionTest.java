package com.jbohorquez.emazon_hexagonal.infrastructure.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AllExistsExceptionTest {

    @Test
    void testAllExistsExceptionIsThrown() {
        assertThrows(AllExistsException.class, () -> {
            throw new AllExistsException();
        });
    }
}