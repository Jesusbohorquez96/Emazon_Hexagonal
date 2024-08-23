package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.repository;

import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {

    Optional<BrandEntity> findById(Long brandId);

    Optional<BrandEntity> findByName(String brName);

    void deleteById(Long brandId);
}
