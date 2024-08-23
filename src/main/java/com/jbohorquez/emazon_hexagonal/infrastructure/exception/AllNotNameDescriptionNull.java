package com.jbohorquez.emazon_hexagonal.infrastructure.exception;

public class AllNotNameDescriptionNull extends RuntimeException {
    public AllNotNameDescriptionNull(String nameOrDescriptionIsEmpty) {
        super();
    }
}
