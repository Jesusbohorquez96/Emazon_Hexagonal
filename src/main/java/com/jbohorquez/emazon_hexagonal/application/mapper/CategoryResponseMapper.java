package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.CategoryResponse;
import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import com.jbohorquez.emazon_hexagonal.infrastructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@Mapper(componentModel = SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {Category.class})
public interface CategoryResponseMapper {

    @Mapping(target = CATEGORY_ID, source = ID)
    @Mapping(target = CATEGORY_NAME, source = NAME)
    @Mapping(target = CATEGORY_DESCRIPTION, source = DESCRIPTION)
    CategoryResponse toResponseList(Category category);

    @Mapping(target = CATEGORY_ID, source = ID)
    @Mapping(target = CATEGORY_NAME, source = NAME)
    @Mapping(target = CATEGORY_DESCRIPTION, source = DESCRIPTION)
    CategoryResponse toResponseList(CategoryEntity categoryEntity);
}
