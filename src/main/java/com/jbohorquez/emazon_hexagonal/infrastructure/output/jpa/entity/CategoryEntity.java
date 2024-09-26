package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.Set;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@Entity
@Table(name = CATEGORY)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length= NAME_MAX_LENGTH, nullable = false)
    @Size(message = NAME_TOO_LONG, max = NAME_MAX_LENGTH)
    private String name;

    @Column(length = DESCRIPTION_MAX_LENGTH, nullable = false)
    @Size(message = DESCRIPTION_TOO_LONG, max = DESCRIPTION_MAX_LENGTH)
    private String description;

    @ManyToMany(mappedBy = CATEGORIES)
    private Set<ArticleEntity> articles;
}
