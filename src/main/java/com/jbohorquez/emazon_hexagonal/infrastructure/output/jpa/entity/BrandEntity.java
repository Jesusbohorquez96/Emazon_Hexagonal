package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Set;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.DESCRIPTION_BRAND_MAX_LENGTH;
import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.NAME_MAX_LENGTH;


@Entity
@Table(name = "brand")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Not null max NAME_MAX_LENGTH characters
    @Column(length= NAME_MAX_LENGTH, nullable = false)
    private String name;

    @Column(name = "description", nullable = false, length = DESCRIPTION_BRAND_MAX_LENGTH)
    @NotBlank(message = "Brand description is mandatory")
    @Size(max = DESCRIPTION_BRAND_MAX_LENGTH, message = "Brand description cannot be more than DESCRIPTION_BRAND_MAX_LENGTH characters")
    private String description;


    @OneToMany(mappedBy = "brand")
    private Set<ArticleEntity> articles;
}
