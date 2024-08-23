package com.jbohorquez.emazon_hexagonal.application.handler;

import com.jbohorquez.emazon_hexagonal.application.dto.CategoryRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.CategoryResponse;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ICategoriesHandler {

    Page<CategoryResponse> getCategories(int page, int size, String sortDirection);

    void saveCategoryInCategory(CategoryRequest categoryRequest);

    List<CategoryResponse> getCategoryFromCategory();

    CategoryResponse getCategoryFromCategory(Long categoryId);

    void updateCategoryInCategory(CategoryRequest categoryRequest);

    void deleteCategoryFromCategory(Long categoryId);
}
