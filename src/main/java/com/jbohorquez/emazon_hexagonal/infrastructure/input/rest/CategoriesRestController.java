package com.jbohorquez.emazon_hexagonal.infrastructure.input.rest;

import com.jbohorquez.emazon_hexagonal.application.dto.CategoryRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.CategoryResponse;
import com.jbohorquez.emazon_hexagonal.application.handler.ICategoriesHandler;
import lombok.RequiredArgsConstructor;
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
