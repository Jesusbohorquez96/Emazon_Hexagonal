package com.jbohorquez.emazon_hexagonal.application.handler;

import com.jbohorquez.emazon_hexagonal.application.dto.CategoryRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.CategoryResponse;
import com.jbohorquez.emazon_hexagonal.application.mapper.CategoryRequestMapper;
import com.jbohorquez.emazon_hexagonal.application.mapper.CategoryResponseMapper;
import com.jbohorquez.emazon_hexagonal.domain.api.ICategoryServicePort;
import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriesHandler implements ICategoriesHandler {

    private final CategoryRequestMapper categoryRequestMapper;
    private final CategoryResponseMapper categoryResponseMapper;
    private final ICategoryServicePort categoryServicePort;

    @Override
    public Page<CategoryResponse> getCategories(int page, int size, String sortDirection) {
        return categoryServicePort.getCategories(page, size, sortDirection)
                .map(categoryResponseMapper::toResponseList);
    }

    @Override
    public void saveInCategory(CategoryRequest categoryRequest) {
        Category category = categoryRequestMapper.toCategory(categoryRequest);
        categoryServicePort.saveCategory(category);
    }

    @Override
    public List<CategoryResponse> getFromCategory() {
        List<Category> category = categoryServicePort.getAllCategory();
        return category.stream()
                .map(categoryResponseMapper::toResponseList)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getFromCategory(Long categoryId) {
        Category category = categoryServicePort.getCategoryById(categoryId);
        return categoryResponseMapper.toResponseList(category);
    }

    @Override
    public void updateInCategory(CategoryRequest categoryRequest) {
        Category category = categoryRequestMapper.toCategory(categoryRequest);
        categoryServicePort.updateCategory(category);
    }

    @Override
    public void deleteFromCategory(Long categoryId) {
        categoryServicePort.deleteCategory(categoryId);
    }
}
