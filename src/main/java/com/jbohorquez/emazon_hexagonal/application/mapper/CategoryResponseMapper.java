package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.CategoryResponse;
import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {Category.class})
public interface CategoryResponseMapper {

    @Mapping(target = "categoryId", source = "id")
    @Mapping(target = "categoryName", source = "name")
    @Mapping(target = "categoryDescription", source = "description")
    CategoryResponse toResponseList(Category category);
}
