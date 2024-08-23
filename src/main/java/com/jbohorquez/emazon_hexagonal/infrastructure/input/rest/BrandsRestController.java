package com.jbohorquez.emazon_hexagonal.infrastructure.input.rest;

import com.jbohorquez.emazon_hexagonal.application.dto.BrandRequest;
import com.jbohorquez.emazon_hexagonal.application.dto.BrandResponse;
import com.jbohorquez.emazon_hexagonal.application.handler.BrandsHandler;
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
public class BrandsRestController {

    private final BrandsHandler brandsHandler;

    @GetMapping
    public ResponseEntity<Page<BrandResponse>> getBrands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Page<BrandResponse> brands = brandsHandler.getBrands(page, size, sortDirection);
        return ResponseEntity.ok(brands);
    }

    @PostMapping("/")
    public ResponseEntity<Void> saveBrandInBrand(@Valid @RequestBody BrandRequest brandRequest) {
        System.out.println("brandRequest = " + brandRequest);
        brandsHandler.saveBrandInBrand(brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<BrandResponse>> getBrandFromBrand() {
        return ResponseEntity.ok(brandsHandler.getBrandFromBrand());
    }

    @GetMapping("/{brId}")
    public ResponseEntity<BrandResponse> getBrandFromBrand(@PathVariable(name = "brId") Long brandId) {
        return ResponseEntity.ok(brandsHandler.getBrandFromBrand(brandId));
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateBrandInBrand(@Valid @RequestBody BrandRequest brandRequest) {
        brandsHandler.updateBrandInBrand(brandRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{brandId}")
    public ResponseEntity<Void> deleteBrandFromBrand(@PathVariable Long brandId) {
        brandsHandler.deleteBrandFromBrand(brandId);
        return ResponseEntity.noContent().build();
    }
}
