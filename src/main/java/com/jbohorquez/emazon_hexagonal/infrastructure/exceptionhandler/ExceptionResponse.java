package com.jbohorquez.emazon_hexagonal.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    CATEGORY_NOT_FOUND("No Category was found with that name"),
    CATEGORY_ALREADY_EXISTS("There is already a category with that name"),
    NO_DATA_FOUND("No data found for the requested petition"),
    ULTRA_STEP_CHARACTERS("too many characters"),;
    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}