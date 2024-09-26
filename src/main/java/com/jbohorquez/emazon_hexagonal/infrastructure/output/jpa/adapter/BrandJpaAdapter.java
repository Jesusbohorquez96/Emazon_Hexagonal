package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.adapter;

import com.jbohorquez.emazon_hexagonal.domain.model.Brand;
import com.jbohorquez.emazon_hexagonal.domain.spi.BrandPersistencePort;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.*;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.BrandEntity;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper.BrandEntityMapper;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@RequiredArgsConstructor
public class BrandJpaAdapter implements BrandPersistencePort {

    private final IBrandRepository brandRepository;

    private final BrandEntityMapper brandEntityMapper;

    @Override
    public void saveBrand(Brand brand) {
        if (brandRepository.findByName(brand.getName()).isPresent()) {
            throw new AlreadyExistsException();
        }
        if (brand.getName().length() > NAME_MAX_LENGTH) {
            throw new NameTooLongException(NAME_TOO_LONG);
        }
        if (brand.getDescription() == null || brand.getDescription().length() > DESCRIPTION_BRAND_MAX_LENGTH) {
            throw new DescriptionTooLongException(DESCRIPTION_TOO_LONG);
        }
        brandRepository.save(brandEntityMapper.toEntity(brand));
    }

    @Override
    public List<Brand> getAllBrand() {
            List<BrandEntity> brandEntityList = brandRepository.findAll();
            if (brandEntityList.isEmpty()) {
                throw new NoDataFoundException();
            }
            return brandEntityMapper.toBrandList(brandEntityList);
    }

    @Override
    public Brand getBrandById(Long brandId) {
        return brandEntityMapper.toBrand(brandRepository.findById(brandId)
                .orElseThrow(AlreadyExistsException::new));
    }

    @Override
    public void updateBrand(Brand brand) {
        if (brandRepository.findByName(brand.getName()).isPresent()) {
            throw new AlreadyExistsException();
        }
        if (brandRepository.findByName(brand.getName()).isPresent()) {
            throw new AlreadyExistsException();
        }
        brandRepository.save(brandEntityMapper.toEntity(brand));
    }

    @Override
    public void deleteBrand(Long brandId) {
        brandRepository.deleteById(brandId);
    }

    @Override
    public Page<Brand> getBrands(PageRequest pageRequest) {
        Page<BrandEntity> brandEntityPage = brandRepository.findAll(pageRequest);
        if (brandEntityPage.isEmpty()) {
            throw new NoDataFoundException();
        }
        return brandEntityPage.map(brandEntityMapper::toBrand);
    }

    @Override
    public Page<Brand> findAll(Pageable pageable) {
        return brandRepository.findAll(pageable).map(brandEntityMapper::toBrand);
    }
}

