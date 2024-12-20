package com.jbohorquez.emazon_hexagonal.domain.usecase;

import com.jbohorquez.emazon_hexagonal.domain.api.ICategoryServicePort;
import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import com.jbohorquez.emazon_hexagonal.domain.spi.ICategoryPersistencePort;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.DescriptionTooLongException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.NameTooLongException;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

public  class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public Page<CategoryEntity> getCategories(int page, int size, String sortBy, boolean ascending) {
        String sortDirection = ascending ? ASC : DESC;
        return categoryPersistencePort.getCategories(page, size, sortBy, sortDirection);
    }

    @Override
    public void saveCategory(Category category) {
        if (category.getName().length() > NAME_MAX_LENGTH) {
            throw new NameTooLongException(NAME_TOO_LONG);
        }
        if (category.getDescription() == null || category.getDescription().length() > DESCRIPTION_MAX_LENGTH) {
            throw new DescriptionTooLongException(DESCRIPTION_TOO_LONG);
        }
      categoryPersistencePort.saveCategory(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryPersistencePort.getAllCategory();
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryPersistencePort.getCategoryById(categoryId);
    }

    @Override
    public void updateCategory(Category category) {
        categoryPersistencePort.updateCategory(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryPersistencePort.deleteCategory(categoryId);
    }

    @Override
    public Page<Category> getCategories(int pageNumber, int pageSize, String sortDirection) {
        Sort sort = Sort.by(NAME);
        if (DESC.equalsIgnoreCase(sortDirection)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return categoryPersistencePort.findAll(pageable);
    }
}
