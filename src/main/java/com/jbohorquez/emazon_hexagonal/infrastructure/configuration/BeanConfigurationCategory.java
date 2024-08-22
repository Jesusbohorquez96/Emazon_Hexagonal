package com.jbohorquez.emazon_hexagonal.infrastructure.configuration;


import com.jbohorquez.emazon_hexagonal.domain.api.ICategoryServicePort;
import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import com.jbohorquez.emazon_hexagonal.domain.spi.ICategoryPersistencePort;
import com.jbohorquez.emazon_hexagonal.domain.usecase.CategoryUseCase;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationCategory {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort()) {
            @Override
            public Page<Category> getCategories(int page, int size, boolean ascending) {
                return null;
            }
        };
    }
}
