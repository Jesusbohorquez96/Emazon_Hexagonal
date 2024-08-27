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
@Tag(name = "Categories", description = "API para la gestión de categorías")
public class CategoriesRestController {

    private final ICategoriesHandler categoriesHandler;

    @Operation(summary = "Obtener categorías paginadas", description = "Devuelve una lista paginada de categorías.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de categorías devuelta exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
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

    @Operation(summary = "Guardar una nueva categoría", description = "Guarda una nueva categoría en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoría creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveInCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoriesHandler.saveCategoryInCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Obtener todas las categorías", description = "Devuelve una lista de todas las categorías.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de categorías devuelta exitosamente")
    })
    @GetMapping("/")
    public ResponseEntity<List<CategoryResponse>> getFromCategory() {
        return ResponseEntity.ok(categoriesHandler.getCategoryFromCategory());
    }

    @Operation(summary = "Obtener una categoría por ID", description = "Devuelve una categoría específica basada en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría devuelta exitosamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getFromCategory(@PathVariable(name = "id") Long categoryId) {
        return ResponseEntity.ok(categoriesHandler.getCategoryFromCategory(categoryId));
    }

    @Operation(summary = "Actualizar una categoría", description = "Actualiza una categoría existente en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoría actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @PutMapping("/")
    public ResponseEntity<Void> updateInCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoriesHandler.updateCategoryInCategory(categoryRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Eliminar una categoría", description = "Elimina una categoría existente basada en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoría eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteFromCategory(@PathVariable Long categoryId) {
        categoriesHandler.deleteCategoryFromCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
