package com.jbohorquez.emazon_hexagonal.application.handler;

import com.jbohorquez.emazon_hexagonal.application.dto.BrandRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.BrandResponse;
import org.springframework.data.domain.Page;

import java.util.List;


public interface BrandsHandler {

    Page<BrandResponse> getBrands(int page, int size, String sortDirection);

    void saveInBrand(BrandRequest brandRequest);

    List<BrandResponse> getFromBrand();

    BrandResponse getFromBrand(Long brandId);

    void updateInBrand(BrandRequest brandRequest);

    void deleteFromBrand(Long brandId);
}
