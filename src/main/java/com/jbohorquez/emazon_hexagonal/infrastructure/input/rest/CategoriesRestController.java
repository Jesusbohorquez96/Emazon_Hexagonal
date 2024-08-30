package com.jbohorquez.emazon_hexagonal.infrastructure.input.rest;

import com.jbohorquez.emazon_hexagonal.application.dto.CategoryRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.CategoryResponse;
import com.jbohorquez.emazon_hexagonal.application.handler.ICategoriesHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Categories", description = "API for category management")
public class CategoriesRestController {

    private final ICategoriesHandler categoriesHandler;

    @Operation(summary = "Get paginated categories", description = "Returns a paginated list of categories.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category list returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> getCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Page<CategoryResponse> categories = categoriesHandler.getCategories(page, size, sortDirection);
        return ResponseEntity.ok(categories);
    }

    @Operation(summary = "Save a new category", description = "Saves a new category to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveInCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoriesHandler.saveCategoryInCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all categories", description = "Returns a list of all categories.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category list returned successfully")
    })
    @GetMapping("/")
    public ResponseEntity<List<CategoryResponse>> getFromCategory() {
        return ResponseEntity.ok(categoriesHandler.getCategoryFromCategory());
    }

    @Operation(summary = "Get a category by ID", description = "Returns a specific category based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category returned successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getFromCategory(@PathVariable(name = "id") Long categoryId) {
        return ResponseEntity.ok(categoriesHandler.getCategoryFromCategory(categoryId));
    }

    @Operation(summary = "Update a category", description = "Updates an existing category in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoría actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @PutMapping("/")
    public ResponseEntity<Void> updateInCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoriesHandler.updateCategoryInCategory(categoryRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a category", description = "Delete an existing category based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteFromCategory(@PathVariable Long categoryId) {
        categoriesHandler.deleteCategoryFromCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
