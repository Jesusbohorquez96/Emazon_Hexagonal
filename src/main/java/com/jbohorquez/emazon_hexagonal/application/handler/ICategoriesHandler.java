package com.jbohorquez.emazon_hexagonal.application.handler;

import com.jbohorquez.emazon_hexagonal.application.dto.CategoryRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.CategoryResponse;
import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ICategoriesHandler {

    Page<Category> getCategories(int page, int size, String sortDirection);

    void saveCategoryInCategory(CategoryRequest categoryRequest);

    List<CategoryResponse> getCategoryFromCategory();

    CategoryResponse getCategoryFromCategory(Long categoryId);

    void updateCategoryInCategory(CategoryRequest categoryRequest);

    void deleteCategoryFromCategory(Long categoryId);
}
