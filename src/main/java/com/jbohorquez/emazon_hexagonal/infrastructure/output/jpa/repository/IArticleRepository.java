package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.repository;

import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {

    Optional<ArticleEntity> findById(Long articleId);

    Optional<ArticleEntity> findByName(String articleName);

    void deleteById(Long articleId);

    @Query("SELECT a FROM ArticleEntity a " +
            "JOIN a.categories c " +
            "WHERE a.brand.name = :brandName " +
            "AND c.name = :categoryName " +
            "AND a.id IN :articleIds")
    Page<ArticleEntity> findByBrandNameAndCategoryNameAndIds(
            @Param("brandName") String brandName,
            @Param("categoryName") String categoryName,
            @Param("articleIds") List<Long> articleIds,
            Pageable pageable);

    @Query("SELECT a FROM ArticleEntity a " +
            "JOIN a.categories c " +
            "WHERE c.name = :categoryName " +
            "AND a.id IN :articleIds")
    Page<ArticleEntity> findByCategoryAndIds(
            @Param("categoryName") String categoryName,
            @Param("articleIds") List<Long> articleIds,
            Pageable pageable);

    @Query("SELECT a FROM ArticleEntity a " +
            "WHERE a.brand.name = :brandName " +
            "AND a.id IN :articleIds")
    Page<ArticleEntity> findByBrandNameAndIds(
            @Param("brandName") String brandName,
            @Param("articleIds") List<Long> articleIds,
            Pageable pageable);

    @Query("SELECT a FROM ArticleEntity a " +
            "WHERE a.id IN :articleIds")
    Page<ArticleEntity> findByIds(
            @Param("articleIds") List<Long> articleIds,
            Pageable pageable);
}
