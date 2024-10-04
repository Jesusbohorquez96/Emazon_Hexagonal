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
    public static final String ASC = "ASC";
    public static final String DESC = "DESC";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String STOCK = "stock";
    public static final String PRICE = "price";
    public static final String BRAND = "brand";
    public static final String CATEGORY = "category";
    public static final String CATEGORIES = "categories";

    public static final String GET_ARTICLE = "/articles";
    public static final String ARTICLE = "article";
    public static final String ARTICLE_ID = "articleId";
    public static final String ARTICLE_NAME = "articleName";
    public static final String ARTICLE_DESCRIPTION = "articleDescription";
    public static final String ARTICLE_STOCK = "articleStock";
    public static final String ARTICLE_PRICE = "articlePrice";
    public static final String ARTICLE_BRAND = "articleBrand";
    public static final String ARTICLE_CATEGORIES = "articleCategories";

    public static final String BRAND_ID = "brandId";
    public static final String BRAND_NAME = "brandName";
    public static final String BRAND_DESCRIPTION = "brandDescription";
    public static final String CATEGORY_ID = "categoryId";
    public static final String CATEGORY_NAME = "categoryName";
    public static final String CATEGORY_DESCRIPTION = "categoryDescription";
    public static final String CATEGORIES_API = "API for category management";

    public static final String BRAND_ID_PATH = "brand_id";
    public static final String CATEGORY_ID_PATH = "category_id";
    public static final String ARTICLE_ID_PATH = "article_id";
    public static final String ARTICLE_CATEGORY = "article_category";
    public static final String ARTICLE_MANAGEMENT = "Article management";
    public static final String BRAND_MANAGEMENT = "Brand management";
    public static final String INCREASE_STOCK_ARTICLE_ID = "/increase-stock/{articleId}";

    public static final String PRIVATE = "294A404E635266556A586E327235753878214125442A472D4B6150645367566B";
    public static final String SWAGGER_UI = "/swagger-ui.html";
    public static final String SWAGGER_UI_RESOURCES = "/swagger-ui/**";
    public static final String ROL = "rol";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String ROLE =  "ROLE_";
    public static final String ADDITIONAL_STOCK = "additionalStock";

    public static final String STOCK_NEGATIVE = "Stock cannot be negative";
    public static final String ARTICLE_NOT_FOUND = "Article not found";
    public static final String STOCK_UPDATED = "Stock updated successfully";

    public static final String STOCK_MANDATORY = "Stock is mandatory";
    public static final String ARTICLE_DESCRIPTION_MANDATORY = "Article description is mandatory";
    public static final String ARTICLE_NAME_MANDATORY = "Article name is mandatory";
    public static final String PRICE_MANDATORY = "Price is mandatory";
    public static final String PRICE_NEGATIVE = "Price cannot be negative";
    public static final String PRICE_INVALID = "Price format is invalid";
    public static final String CATEGORY_REQUIRED = "At least one category is required";
    public static final String BRAND_ID_MANDATORY = "Brand ID is mandatory";
    public static final String BRAND_DESCRIPTION_IS_MANDATORY = "Brand description is mandatory";
    public static final String BRAND_DESCRIPTION_TOO_LONG = "Brand description cannot be more than 120 characters";

    public static final String ARTICLE_CATEGORIES_LIMIT = "Article can have 1 to 3 categories";
    public static final String ARTICLE_MINIMUM_ONE_CATEGORY = "Article must have at least one category";
    public static final String CATEGORIES_MUST_BE_DIFFERENT = "Categories must be different";
    public static final String NAME_TOO_LONG = "Name is too long";
    public static final String DESCRIPTION_TOO_LONG = "Description is too long";

    public static final String NAME_BLANK = "Name cannot be blank";
    public static final String NAME_REQUIRED = "The name must not exceed " + NAME_MAX_LENGTH + " characters";
    public static final String DESCRIPTION_BLANK = "Description cannot be blank";
    public static final String DESCRIPTION_REQUIRED = "The description must not exceed " + DESCRIPTION_MAX_LENGTH + " characters";
    public static final String DESCRIPTION_BRAND_REQUIRED = "The description must not exceed " + DESCRIPTION_BRAND_MAX_LENGTH + " characters";
    public static final String MESSAGE = "message";
    public static final String SPRING = "spring";
    public static final String ERROR = "error";
    public static final String JWT_TOKEN = "JWT Token";
    public static final String MALFORMED_JWT = "Malformed JWT Exception: ";
    public static final String INVALID_JWT = "Invalid or malformed JWT token";

    public static final String TITLE = "Hexagonal Monolithic Category API";
    public static final String SWAGGER_TERMS = "http://swagger.io/terms/";
    public static final String SWAGGER_LICENSE = "Apache 2.0";
    public static final String SWAGGER_LICENSE_URL = "http://springdoc.org";

    public static final String TODO_ROL = "hasAnyRole('admin', 'aux_bodega', 'customer')";
    public static final String ROL_ADMIN = "hasAnyRole('admin')";
    public static final String ROL_ADMIN_AUX= "hasAnyRole('admin', 'aux_bodega')";

    public static final String ROOT = "/";
    public static final String GET_ID = "/{id}";
    public static final String GET_ARTICLE_ID = "/{articleId}";
    public static final String GET_BRAND = "/brands";
    public static final String GET_BRAND_ID = "/{brandId}";
    public static final String DELETE_ARTICLE_ID = "/{articleId}";
    public static final String GET_CATEGORIES = "/categories";
    public static final String GET_CATEGORY_ID = "/{categoryId}";

    public static final String V3_API = "/v3/api-docs/**";
    public static final String AUTH = "/auth/**";
    public static final String ALL_API = "/api/**";
    public static final String ALL = "*";
    public static final String[] ALLOWED_METHODS = {"GET", "POST", "PUT", "DELETE", "OPTIONS"};
    public static final String HTTP = "http://localhost:4200";


    public static final String JSON = "application/json";
    public static final String ERROR_JWT = "{ \"error\": \"Access denied: Invalid or malformed JWT token\" }";


    private ValidationConstants() {
        throw new IllegalStateException("Utility class");
    }
}
