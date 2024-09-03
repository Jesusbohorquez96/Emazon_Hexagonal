package com.jbohorquez.emazon_hexagonal.constants;

public class ValidationConstants {


    public static final int NAME_MAX_LENGTH = 50;
    public static final int DESCRIPTION_MAX_LENGTH = 90;
    public static final int DESCRIPTION_BRAND_MAX_LENGTH = 120;
    public static final int ZERO = 0;
    public static final int MAX_PHONE = 13;
    public static final int MAX_DOCUMENT = 20;
    public static final String PHONE_NUMBER = "^\\+?[0-9]{1,13}$";

    private ValidationConstants() {
        throw new IllegalStateException("Utility class");
    }
}
