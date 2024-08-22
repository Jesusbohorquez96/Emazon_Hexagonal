package com.jbohorquez.emazon_hexagonal.constants;

public class ValidationConstants {
    public static final int NAME_MAX_LENGTH = 50;
    public static final int DESCRIPTION_MAX_LENGTH = 90;

    private ValidationConstants() {
        throw new IllegalStateException("Utility class");
    }
}
