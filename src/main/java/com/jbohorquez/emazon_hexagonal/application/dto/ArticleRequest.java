package com.jbohorquez.emazon_hexagonal.application.dto;

import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;

import java.util.Collections;
import java.util.Set;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.DESCRIPTION_BRAND_MAX_LENGTH;
import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.NAME_MAX_LENGTH;

@Data
@Getter
@Setter
public class ArticleRequest {

    @Column(length= NAME_MAX_LENGTH, nullable = false)
    @NotBlank(message = "Article name is mandatory")
    @Size(max = NAME_MAX_LENGTH)
    private String name;

    @Column(name = "description", nullable = false, length = DESCRIPTION_BRAND_MAX_LENGTH)
    @NotBlank(message = "Article description is mandatory")
    @Size(max = DESCRIPTION_BRAND_MAX_LENGTH)
    private String description;

    @Column(nullable = false)
    @NotNull(message = "Stock is mandatory")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;

    @Column(nullable = false)
    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "Price cannot be negative")
    @Digits(integer = 10, fraction = 2, message = "Price format is invalid")
    private Double price;

    @NotEmpty(message = "At least one category is required")
    private Set<Long> categories;

    @NotNull(message = "Brand ID is mandatory")
    private Long brand;



}
