package com.jbohorquez.emazon_hexagonal.application.handler;

import com.jbohorquez.emazon_hexagonal.application.dto.BrandRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.BrandResponse;
import org.springframework.data.domain.Page;

import java.util.List;


public interface BrandsHandler {

    Page<BrandResponse> getBrands(int page, int size, String sortDirection);

    void saveBrandInBrand(BrandRequest brandRequest);

    List<BrandResponse> getBrandFromBrand();

    BrandResponse getBrandFromBrand(Long brandId);

    void updateBrandInBrand(BrandRequest brandRequest);

    void deleteBrandFromBrand(Long brandId);
}
