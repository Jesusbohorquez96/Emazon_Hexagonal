package com.jbohorquez.emazon_hexagonal.application.handler;

import com.jbohorquez.emazon_hexagonal.application.dto.CategoryRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.CategoryResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoriesHandler {

    Page<CategoryResponse> getCategories(int page, int size, String sortBy, String sortDirection);

    void saveInCategory(CategoryRequest categoryRequest);

    List<CategoryResponse> getFromCategory();

    CategoryResponse getFromCategory(Long categoryId);

    void updateInCategory(CategoryRequest categoryRequest);

    void deleteFromCategory(Long categoryId);
}
