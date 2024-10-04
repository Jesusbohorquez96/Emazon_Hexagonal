package com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.adapter;

import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import com.jbohorquez.emazon_hexagonal.domain.spi.ICategoryPersistencePort;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.*;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.CategoryEntity;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        if (category.getName().length() > NAME_MAX_LENGTH) {
            throw new NameTooLongException(NAME_TOO_LONG);
        }
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new AlreadyExistsException();
        }
        if (category.getDescription() == null || category.getDescription().length() > DESCRIPTION_MAX_LENGTH) {
            throw new DescriptionTooLongException(DESCRIPTION_TOO_LONG);
        }
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public List<Category> getAllCategory() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        if (categoryEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return categoryEntityMapper.toCategoryList(categoryEntityList);
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryEntityMapper.toCategory(categoryRepository.findById(categoryId)
                .orElseThrow(AlreadyExistsException::new));
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Page<Category> getCategories(PageRequest pageRequest) {
        Page<CategoryEntity> categoryEntityPage = categoryRepository.findAll(pageRequest);
        if (categoryEntityPage.isEmpty()) {
            throw new NoDataFoundException();
        }
        return categoryEntityPage.map(categoryEntityMapper::toCategory);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(categoryEntityMapper::toCategory);
    }

    @Override
    public Page<CategoryEntity> getCategories(int page, int size, String sortBy, String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return categoryRepository.findAll(pageable);
    }
}
