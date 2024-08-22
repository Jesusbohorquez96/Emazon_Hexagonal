package com.jbohorquez.emazon_hexagonal.domain.spi;

import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);

    List<Category> getAllCategory();

    Category getCategoryById(Long categoryId);

    void updateCategory(Category category);

    void deleteCategory(Long categoryId);

    Page<Category> getCategories(PageRequest pageRequest);

    Page<Category> findAll(Pageable pageable);
}
