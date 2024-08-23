package com.jbohorquez.emazon_hexagonal.domain.spi;

import com.jbohorquez.emazon_hexagonal.domain.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BrandPersistencePort {

    void saveBrand(Brand brand);

    List<Brand> getAllBrand();

    Brand getBrandById(Long brandId);

    void updateBrand(Brand brand);

    void deleteBrand(Long brandId);

    Page<Brand> getBrands(PageRequest pageRequest);

    Page<Brand> findAll(Pageable pageable);
}
