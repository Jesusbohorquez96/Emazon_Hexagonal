package com.jbohorquez.emazon_hexagonal.application.handler;

import com.jbohorquez.emazon_hexagonal.application.dto.CategoryRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.CategoryResponse;

import java.util.List;


public interface ICategoriesHandler {

    void saveCategoryInCategory(CategoryRequest categoryRequest);

    List<CategoryResponse> getCategoryFromCategory();

    CategoryResponse getCategoryFromCategory(Long categoryId);

    void updateCategoryInCategory(CategoryRequest categoryRequest);

    void deleteCategoryFromCategory(Long categoryId);
}
