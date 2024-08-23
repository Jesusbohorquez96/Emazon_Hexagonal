package com.jbohorquez.emazon_hexagonal.application.handler;

import com.jbohorquez.emazon_hexagonal.application.dto.BrandRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.BrandResponse;
import com.jbohorquez.emazon_hexagonal.application.mapper.BrandRequestMapper;
import com.jbohorquez.emazon_hexagonal.application.mapper.BrandResponseMapper;
import com.jbohorquez.emazon_hexagonal.domain.api.BrandServicePort;
import com.jbohorquez.emazon_hexagonal.domain.model.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class IBrandsHandler implements BrandsHandler {

    private final BrandRequestMapper brandRequestMapper;
    private final BrandResponseMapper brandResponseMapper;
    private final BrandServicePort brandServicePort;

    @Override
    public Page<BrandResponse> getBrands(int page, int size, String sortDirection) {
        return brandServicePort.getBrands(page, size, sortDirection)
                .map(brandResponseMapper::toBrResponseList);
    }

    @Override
    public void saveBrandInBrand(BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.toBrand(brandRequest);
        brandServicePort.saveBrand(brand);
    }

    @Override
    public List<BrandResponse> getBrandFromBrand() {
        List<Brand> brand = brandServicePort.getAllBrand();
        return brand.stream()
                .map(brandResponseMapper::toBrResponseList)
                .collect(Collectors.toList());
    }

    @Override
    public BrandResponse getBrandFromBrand(Long brandId) {
        Brand brand = brandServicePort.getBrandById(brandId);
        return brandResponseMapper.toBrResponseList(brand);
    }

    @Override
    public void updateBrandInBrand(BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.toBrand(brandRequest);
        brandServicePort.updateBrand(brand);
    }

    @Override
    public void deleteBrandFromBrand(Long brandId) {
        brandServicePort.deleteBrand(brandId);
    }
}
