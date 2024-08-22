package com.jbohorquez.emazon_hexagonal.infrastructure.input.rest;

import com.jbohorquez.emazon_hexagonal.application.dto.CategoryRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.CategoryResponse;
import com.jbohorquez.emazon_hexagonal.application.handler.ICategoriesHandler;
import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoriesRestController {

    private final ICategoriesHandler categoriesHandler;

    @GetMapping
    public ResponseEntity<Page<Category>> getCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Page<Category> categories = categoriesHandler.getCategories(page, size, sortDirection);
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/")
    public ResponseEntity<Void> saveCategoryInCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoriesHandler.saveCategoryInCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryResponse>> getCategoryFromCategory() {
        return ResponseEntity.ok(categoriesHandler.getCategoryFromCategory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryFromCategory(@PathVariable(name = "id") Long categoryId) {
        return ResponseEntity.ok(categoriesHandler.getCategoryFromCategory(categoryId));
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateCategoryInCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoriesHandler.updateCategoryInCategory(categoryRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategoryFromCategory(@PathVariable Long categoryId) {
        categoriesHandler.deleteCategoryFromCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
