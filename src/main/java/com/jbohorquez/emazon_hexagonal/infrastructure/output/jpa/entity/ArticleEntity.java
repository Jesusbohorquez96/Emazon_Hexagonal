package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;


import java.util.Set;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.DESCRIPTION_BRAND_MAX_LENGTH;
import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.NAME_MAX_LENGTH;


@Entity
@Table(name = "article")
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

    @Column(name = "description", nullable = false, length = DESCRIPTION_BRAND_MAX_LENGTH)
    @NotBlank(message = "Brand description is mandatory")
    @Size(max = DESCRIPTION_BRAND_MAX_LENGTH, message = "Brand description cannot be more than DESCRIPTION_BRAND_MAX_LENGTH characters")
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
    @Size(min = 1, max = 3, message = "At least one category is required")

    @ManyToMany
    @JoinTable(
            name = "article_category",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoryEntity> categories;
}
