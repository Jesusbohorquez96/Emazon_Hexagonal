package com.jbohorquez.emazon_hexagonal.infrastructure.input.rest;

import com.jbohorquez.emazon_hexagonal.application.dto.BrandRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.BrandResponse;
import com.jbohorquez.emazon_hexagonal.application.handler.BrandsHandler;
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
@RequestMapping(GET_BRAND)
@RequiredArgsConstructor
@Tag(name = BRAND, description = BRAND_MANAGEMENT)
public class BrandsRestController {

    private final BrandsHandler brandsHandler;

    @Operation(summary = "Get paginated bookmarks", description = "Returns a paginated list of tags.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of brands returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @GetMapping
//    @PreAuthorize(TODO_ROL)
    public ResponseEntity<Page<BrandResponse>> getBrands(
            @RequestParam(defaultValue = PAGE) int page,
            @RequestParam(defaultValue = SIZE) int size,
            @RequestParam(defaultValue = ASC) String sortDirection
    ) {
        Page<BrandResponse> brands = brandsHandler.getBrands(page, size, sortDirection);
        return ResponseEntity.ok(brands);
    }

    @Operation(summary = "Save a new brand", description = "Saves a new mark to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })

    @PostMapping
    @PreAuthorize(ROL_ADMIN_AUX)
    public ResponseEntity<Map<String, String>> saveInBrand(@Valid @RequestBody BrandRequest brandRequest) {
        try {
            brandsHandler.saveInBrand(brandRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Collections.singletonMap(MESSAGE, ExceptionResponse.SUCCESSFUL_CREATION.getMessage()));
        } catch (AllExistsException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INTERNAL_ERROR.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ALREADY_EXISTS.getMessage()));
        }
    }

    @Operation(summary = "Get all brands", description = "Returns a list of all brands.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of brands returned successfully")
    })
    @GetMapping(ROOT)
//    @PreAuthorize(TODO_ROL)
    public ResponseEntity<List<BrandResponse>> getFromBrand() {
        return ResponseEntity.ok(brandsHandler.getFromBrand());
    }

    @Operation(summary = "Get a mark by ID", description = "Returns a specific tag based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand successfully returned"),
            @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    @GetMapping(GET_BRAND_ID)
//    @PreAuthorize(TODO_ROL)
    public ResponseEntity<BrandResponse> getFromBrand(@PathVariable(name = BRAND_ID) Long brandId) {
        return ResponseEntity.ok(brandsHandler.getFromBrand(brandId));
    }

    @Operation(summary = "Update a brand", description = "Updates an existing tag in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Brand successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    @PutMapping(ROOT)
//    @PreAuthorize(ROL_ADMIN)
    public ResponseEntity<Void> updateInBrand(@Valid @RequestBody BrandRequest brandRequest) {
        brandsHandler.updateInBrand(brandRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a brand", description = "Deletes an existing tag based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Brand successfully removed"),
            @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    @DeleteMapping(GET_BRAND_ID)
//    @PreAuthorize(ROL_ADMIN)
    public ResponseEntity<Void> deleteFromBrand(@PathVariable Long brandId) {
        brandsHandler.deleteFromBrand(brandId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
