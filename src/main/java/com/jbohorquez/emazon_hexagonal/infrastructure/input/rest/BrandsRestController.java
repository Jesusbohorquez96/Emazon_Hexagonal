package com.jbohorquez.emazon_hexagonal.infrastructure.input.rest;

import com.jbohorquez.emazon_hexagonal.application.dto.BrandRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.BrandResponse;
import com.jbohorquez.emazon_hexagonal.application.handler.BrandsHandler;
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
@RequestMapping("/brands")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Gestión de productos")
public class BrandsRestController {

    private final BrandsHandler brandsHandler;

    @Operation(summary = "Obtener marcas paginadas", description = "Devuelve una lista paginada de marcas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de marcas devuelta exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @GetMapping
    public ResponseEntity<Page<BrandResponse>> getBrands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Page<BrandResponse> brands = brandsHandler.getBrands(page, size, sortDirection);
        return ResponseEntity.ok(brands);
    }

    @Operation(summary = "Guardar una nueva marca", description = "Guarda una nueva marca en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Marca creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveInBrand(@Valid @RequestBody BrandRequest brandRequest) {
        System.out.println("brandRequest = " + brandRequest);
        brandsHandler.saveInBrand(brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Obtener todas las marcas", description = "Devuelve una lista de todas las marcas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de marcas devuelta exitosamente")
    })
    @GetMapping("/")
    public ResponseEntity<List<BrandResponse>> getFromBrand() {
        return ResponseEntity.ok(brandsHandler.getFromBrand());
    }

    @Operation(summary = "Obtener una marca por ID", description = "Devuelve una marca específica basada en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca devuelta exitosamente"),
            @ApiResponse(responseCode = "404", description = "Marca no encontrada")
    })
    @GetMapping("/{brId}")
    public ResponseEntity<BrandResponse> getFromBrand(@PathVariable(name = "brId") Long brandId) {
        return ResponseEntity.ok(brandsHandler.getFromBrand(brandId));
    }

    @Operation(summary = "Actualizar una marca", description = "Actualiza una marca existente en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Marca actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Marca no encontrada")
    })
    @PutMapping("/")
    public ResponseEntity<Void> updateInBrand(@Valid @RequestBody BrandRequest brandRequest) {
        brandsHandler.updateInBrand(brandRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Eliminar una marca", description = "Elimina una marca existente basada en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Marca eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Marca no encontrada")
    })
    @DeleteMapping("/{brandId}")
    public ResponseEntity<Void> deleteFromBrand(@PathVariable Long brandId) {
        brandsHandler.deleteFromBrand(brandId);
        return ResponseEntity.noContent().build();
    }

}
