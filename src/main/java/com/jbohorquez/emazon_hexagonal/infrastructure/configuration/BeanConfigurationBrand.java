package com.jbohorquez.emazon_hexagonal.infrastructure.configuration;

import com.jbohorquez.emazon_hexagonal.domain.api.BrandServicePort;
import com.jbohorquez.emazon_hexagonal.domain.model.Brand;
import com.jbohorquez.emazon_hexagonal.domain.spi.BrandPersistencePort;
import com.jbohorquez.emazon_hexagonal.domain.usecase.BrandUseCase;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.adapter.BrandJpaAdapter;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper.BrandEntityMapper;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationBrand {

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    @Bean
    public BrandPersistencePort brandPersistencePort() {
        return new BrandJpaAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public BrandServicePort brandServicePort() {
        return new BrandUseCase(brandPersistencePort()) {
            @Override
            public Page<Brand> getBrands(int page, int size, boolean ascending) {
                return null;
            }
        };
    }
}
