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
    void saveInCategory() {
        when(categoryRequestMapper.toCategory(categoryRequest)).thenReturn(category);
        doNothing().when(categoryServicePort).saveCategory(category);

        categoriesHandler.saveInCategory(categoryRequest);

        verify(categoryRequestMapper, times(1)).toCategory(categoryRequest);
        verify(categoryServicePort, times(1)).saveCategory(category);
    }

    @Test
    void getFromCategory() {
        when(categoryServicePort.getAllCategory()).thenReturn(Collections.singletonList(category));
        when(categoryResponseMapper.toResponseList(category)).thenReturn(categoryResponse);

        List<CategoryResponse> result = categoriesHandler.getFromCategory();

        assertEquals(1, result.size());
        assertEquals(categoryResponse, result.get(0));
    }

    @Test
    void getFromCategoryById() {
        when(categoryServicePort.getCategoryById(1L)).thenReturn(category);
        when(categoryResponseMapper.toResponseList(category)).thenReturn(categoryResponse);

        CategoryResponse result = categoriesHandler.getFromCategory(1L);

        assertEquals(categoryResponse, result);
    }

    @Test
    void updateInCategory() {
        when(categoryRequestMapper.toCategory(categoryRequest)).thenReturn(category);
        doNothing().when(categoryServicePort).updateCategory(category);

        categoriesHandler.updateInCategory(categoryRequest);

        verify(categoryRequestMapper, times(1)).toCategory(categoryRequest);
        verify(categoryServicePort, times(1)).updateCategory(category);
    }

    @Test
    void deleteFromCategory() {
        doNothing().when(categoryServicePort).deleteCategory(1L);

        categoriesHandler.deleteFromCategory(1L);

        verify(categoryServicePort, times(1)).deleteCategory(1L);
    }
}