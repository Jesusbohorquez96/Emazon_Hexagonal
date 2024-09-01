package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.repository;

import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {

    Optional<ArticleEntity> findById(Long articleId);

    Optional<ArticleEntity> findByName(String articleName);

    void deleteById(Long articleId);

}
