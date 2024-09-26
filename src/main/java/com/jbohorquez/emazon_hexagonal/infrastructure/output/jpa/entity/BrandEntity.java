package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.List;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@Entity
@Table(name = BRAND)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length= NAME_MAX_LENGTH, nullable = false)
    private String name;

    @Column(name = DESCRIPTION, nullable = false, length = DESCRIPTION_BRAND_MAX_LENGTH)
    @NotBlank(message = BRAND_DESCRIPTION_IS_MANDATORY)
    @Size(max = DESCRIPTION_BRAND_MAX_LENGTH, message = BRAND_DESCRIPTION_TOO_LONG)
    private String description;

    @OneToMany(mappedBy = BRAND)
    private List<ArticleEntity> articles;
}
