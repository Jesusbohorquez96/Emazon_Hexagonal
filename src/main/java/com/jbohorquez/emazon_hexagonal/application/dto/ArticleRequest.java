package com.jbohorquez.emazon_hexagonal.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;

import java.util.Set;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@Data
@Getter
@Setter
public class ArticleRequest {

    @Column(length= NAME_MAX_LENGTH, nullable = false)
    @NotBlank(message = ARTICLE_NAME_MANDATORY)
    @Size(max = NAME_MAX_LENGTH)
    private String name;

    @Column(name = DESCRIPTION, nullable = false, length = DESCRIPTION_BRAND_MAX_LENGTH)
    @NotBlank(message = ARTICLE_DESCRIPTION_MANDATORY)
    @Size(max = DESCRIPTION_BRAND_MAX_LENGTH)
    private String description;

    @Column(nullable = false)
    @NotNull(message = STOCK_MANDATORY)
    @Min(value = ZERO, message = STOCK_NEGATIVE)
    private Integer stock;

    @Column(nullable = false)
    @NotNull(message = PRICE_MANDATORY)
    @Min(value = ZERO, message = PRICE_NEGATIVE)
    @Digits(integer = INTEGERS, fraction = DECIMALS, message = PRICE_INVALID)
    private Double price;

    @NotEmpty(message = CATEGORY_REQUIRED)
    private Set<Long> categories;

    @NotNull(message = BRAND_ID_MANDATORY)
    private Long brand;
}
