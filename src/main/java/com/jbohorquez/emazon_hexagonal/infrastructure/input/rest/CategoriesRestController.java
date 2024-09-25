package com.jbohorquez.emazon_hexagonal.infrastructure.input.rest;

import com.jbohorquez.emazon_hexagonal.application.dto.CategoryRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.CategoryResponse;
import com.jbohorquez.emazon_hexagonal.application.handler.ICategoriesHandler;
import com.jbohorquez.emazon_hexagonal.infrastructure.exception.AllExistsException;
import com.jbohorquez.emazon_hexagonal.infrastructure.exceptionhandler.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

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
    @PreAuthorize("hasAnyRole('admin', 'aux_bodega', 'customer')")
    public ResponseEntity<Page<CategoryResponse>> getCategories(
            @RequestParam(defaultValue = PAGE) int page,
            @RequestParam(defaultValue = SIZE) int size,
            @RequestParam(defaultValue = ASC) String sortDirection
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
    @PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<Map<String, String>> saveInCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        try {
            categoriesHandler.saveInCategory(categoryRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Collections.singletonMap("message", ExceptionResponse.SUCCESSFUL_CREATION.getMessage()));
        } catch (AllExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("message", ExceptionResponse.INTERNAL_ERROR.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", ExceptionResponse.ALREADY_EXISTS.getMessage()));
        }
    }

    @Operation(summary = "Get all categories", description = "Returns a list of all categories.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category list returned successfully")
    })
    @GetMapping("/")
    @PreAuthorize("hasAnyRole('admin', 'aux_bodega', 'customer')")
    public ResponseEntity<List<CategoryResponse>> getFromCategory() {
        return ResponseEntity.ok(categoriesHandler.getFromCategory());
    }

    @Operation(summary = "Get a category by ID", description = "Returns a specific category based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category returned successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin', 'aux_bodega', 'customer')")
    public ResponseEntity<CategoryResponse> getFromCategory(@PathVariable(name = "id") Long categoryId) {
        return ResponseEntity.ok(categoriesHandler.getFromCategory(categoryId));
    }

    @Operation(summary = "Update a category", description = "Updates an existing category in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoría actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @PutMapping("/")
    @PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<Void> updateInCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoriesHandler.updateInCategory(categoryRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a category", description = "Delete an existing category based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @DeleteMapping("/{categoryId}")
    @PreAuthorize("hasAnyRole('admin')")
    public ResponseEntity<Void> deleteFromCategory(@PathVariable Long categoryId) {
        categoriesHandler.deleteFromCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
