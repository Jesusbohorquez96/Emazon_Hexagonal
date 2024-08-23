package com.jbohorquez.emazon_hexagonal.domain.usecase;

import com.jbohorquez.emazon_hexagonal.domain.api.BrandServicePort;
import com.jbohorquez.emazon_hexagonal.domain.model.Brand;
import com.jbohorquez.emazon_hexagonal.domain.spi.BrandPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public abstract class BrandUseCase implements BrandServicePort {

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
        Sort sort = Sort.by("name");
        if ("desc".equalsIgnoreCase(sortDirection)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return brandPersistencePort.findAll(pageable);
    }

    public abstract Page<Brand> getBrands(int page, int size, boolean ascending);
}
