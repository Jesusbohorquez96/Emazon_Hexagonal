package com.jbohorquez.emazon_hexagonal.domain.api;

import com.jbohorquez.emazon_hexagonal.domain.model.Brand;
import org.springframework.data.domain.Page;

import java.util.List;


public interface BrandServicePort {

    void saveBrand(Brand brand);

    List<Brand> getAllBrand();

    Brand getBrandById(Long brandId);

    void updateBrand(Brand brand);

    void deleteBrand(Long brandId);

    Page<Brand> getBrands(int page, int size, boolean ascending);

    Page<Brand> getBrands(int pageNumber, int pageSize, String sortDirection);
}

