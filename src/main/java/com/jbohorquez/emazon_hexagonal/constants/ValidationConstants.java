package com.jbohorquez.emazon_hexagonal.constants;

public class ValidationConstants {

    public static final int ZERO = 0;
    public static final int MIN = 1;
    public static final int DECIMALS = 2;
    public static final int ASSOCIATED = 3;
    public static final int SEVEN = 7;
    public static final int INTEGERS = 10;
    public static final int NAME_MAX_LENGTH = 50;
    public static final int DESCRIPTION_MAX_LENGTH = 90;
    public static final int DESCRIPTION_BRAND_MAX_LENGTH = 120;

    public static final String PAGE = "0";
    public static final String SIZE = "10";
    public static final String ASC = "asc";
    public static final String DESC = "desc";
    public static final String NAME = "name";
    public static final String PRIVATE = "294A404E635266556A586E327235753878214125442A472D4B6150645367566B";
    public static final String SWAGGER_UI = "/swagger-ui.html";
    public static final String SWAGGER_UI_RESOURCES = "/swagger-ui/**";
    public static final String ALL = "/**";
    public static final String ROL = "rol";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String ERROR_AUTHENTICATION = "Error en la authorization JWT: ";
    public static final String TOKEN_INVALID = "Token invalid o expiration";
    public static final String ROLE =  "ROLE_";



    private ValidationConstants() {
        throw new IllegalStateException("Utility class");
    }
}
