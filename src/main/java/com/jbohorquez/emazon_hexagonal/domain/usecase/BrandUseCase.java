package com.jbohorquez.emazon_hexagonal.domain.usecase;

import com.jbohorquez.emazon_hexagonal.domain.api.BrandServicePort;
import com.jbohorquez.emazon_hexagonal.domain.model.Brand;
import com.jbohorquez.emazon_hexagonal.domain.spi.BrandPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.DESC;
import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.NAME;

public class BrandUseCase implements BrandServicePort {

    private final BrandPersistencePort brandPersistencePort;

    public BrandUseCase(BrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
      brandPersistencePort.saveBrand(brand);
    }

    @Override
    public List<Brand> getAllBrand() {
        return brandPersistencePort.getAllBrand();
    }

    @Override
    public Brand getBrandById(Long brandId) {
        return brandPersistencePort.getBrandById(brandId);
    }

    @Override
    public void updateBrand(Brand brand) {
        brandPersistencePort.updateBrand(brand);
    }

    @Override
    public void deleteBrand(Long brandId) {
        brandPersistencePort.deleteBrand(brandId);
    }

    @Override
    public Page<Brand> getBrands(int pageNumber, int pageSize, String sortDirection) {
        Sort sort = Sort.by(NAME);
        if (DESC.equalsIgnoreCase(sortDirection)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return brandPersistencePort.findAll(pageable);
    }
    @Override
    public Page<Brand> getBrands(int page, int size, boolean ascending) {
        Sort sort = ascending ? Sort.by(NAME).ascending() : Sort.by(NAME).descending();
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return brandPersistencePort.findAll(pageRequest);
    }
}
