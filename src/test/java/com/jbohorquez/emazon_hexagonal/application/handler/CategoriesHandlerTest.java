package com.jbohorquez.emazon_hexagonal.application.handler;

import com.jbohorquez.emazon_hexagonal.application.dto.CategoryRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.CategoryResponse;
import com.jbohorquez.emazon_hexagonal.application.mapper.CategoryRequestMapper;
import com.jbohorquez.emazon_hexagonal.application.mapper.CategoryResponseMapper;
import com.jbohorquez.emazon_hexagonal.domain.api.ICategoryServicePort;
import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoriesHandlerTest {

    @Mock
    private CategoryRequestMapper categoryRequestMapper;

    @Mock
    private CategoryResponseMapper categoryResponseMapper;

    @Mock
    private ICategoryServicePort categoryServicePort;

    @InjectMocks
    private CategoriesHandler categoriesHandler;

    private Category category;
    private CategoryRequest categoryRequest;
    private CategoryResponse categoryResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        category = new Category(1L, "CategoryName", "CategoryDescription");
        categoryRequest = new CategoryRequest("CategoryName", "CategoryDescription");
        categoryResponse = new CategoryResponse(1L, "CategoryName", "CategoryDescription");
    }

    @Test
    void getCategories() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Category> categoryPage = new PageImpl<>(Collections.singletonList(category), pageRequest, 1);

        when(categoryServicePort.getCategories(0, 10, "asc")).thenReturn(categoryPage);
        when(categoryResponseMapper.toResponseList(category)).thenReturn(categoryResponse);

        Page<CategoryResponse> result = categoriesHandler.getCategories(0, 10, "asc");

        assertEquals(1, result.getTotalElements());
        assertEquals(categoryResponse, result.getContent().get(0));
    }

    @Test
    void saveCategoryInCategory() {
        when(categoryRequestMapper.toCategory(categoryRequest)).thenReturn(category);
        doNothing().when(categoryServicePort).saveCategory(category);

        categoriesHandler.saveCategoryInCategory(categoryRequest);

        verify(categoryRequestMapper, times(1)).toCategory(categoryRequest);
        verify(categoryServicePort, times(1)).saveCategory(category);
    }

    @Test
    void getCategoryFromCategory() {
        when(categoryServicePort.getAllCategory()).thenReturn(Collections.singletonList(category));
        when(categoryResponseMapper.toResponseList(category)).thenReturn(categoryResponse);

        List<CategoryResponse> result = categoriesHandler.getCategoryFromCategory();

        assertEquals(1, result.size());
        assertEquals(categoryResponse, result.get(0));
    }

    @Test
    void getCategoryFromCategoryById() {
        when(categoryServicePort.getCategoryById(1L)).thenReturn(category);
        when(categoryResponseMapper.toResponseList(category)).thenReturn(categoryResponse);

        CategoryResponse result = categoriesHandler.getCategoryFromCategory(1L);

        assertEquals(categoryResponse, result);
    }

    @Test
    void updateCategoryInCategory() {
        when(categoryRequestMapper.toCategory(categoryRequest)).thenReturn(category);
        doNothing().when(categoryServicePort).updateCategory(category);

        categoriesHandler.updateCategoryInCategory(categoryRequest);

        verify(categoryRequestMapper, times(1)).toCategory(categoryRequest);
        verify(categoryServicePort, times(1)).updateCategory(category);
    }

    @Test
    void deleteCategoryFromCategory() {
        doNothing().when(categoryServicePort).deleteCategory(1L);

        categoriesHandler.deleteCategoryFromCategory(1L);

        verify(categoryServicePort, times(1)).deleteCategory(1L);
    }
}