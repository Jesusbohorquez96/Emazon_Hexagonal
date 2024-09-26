package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.Set;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@Entity
@Table(name = ARTICLE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length= NAME_MAX_LENGTH, nullable = false)
    private String name;

    @Column(name = DESCRIPTION, nullable = false, length = DESCRIPTION_BRAND_MAX_LENGTH)
    @NotBlank(message = BRAND_DESCRIPTION_IS_MANDATORY)
    @Size(max = DESCRIPTION_BRAND_MAX_LENGTH, message = BRAND_DESCRIPTION_TOO_LONG)
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
    @Size(min = MIN, max = ASSOCIATED, message = CATEGORY_REQUIRED)

    @ManyToMany
    @JoinTable(
            name = ARTICLE_CATEGORY,
            joinColumns = @JoinColumn(name = ARTICLE_ID_PATH),
            inverseJoinColumns = @JoinColumn(name = CATEGORY_ID_PATH)
    )
    private Set<CategoryEntity> categories;

    @ManyToOne
    @JoinColumn(name = BRAND_ID_PATH)
    private BrandEntity brand;

}
