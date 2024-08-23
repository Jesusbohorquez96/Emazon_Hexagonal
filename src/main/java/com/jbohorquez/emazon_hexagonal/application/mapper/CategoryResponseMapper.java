package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.CategoryResponse;
import com.jbohorquez.emazon_hexagonal.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;


@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {CategoryDtoMapper.class})
public interface CategoryResponseMapper {

    @Mapping(target = "categoryName", source = "name")
    @Mapping(target = "categoryDescription", source = "description")
    CategoryResponse toResponseList(Category category);


    @Mapping(source = "categoryName", target = "name")
    @Mapping(source = "categoryDescription", target = "description")
    Category toResponseList(CategoryResponse categoryResponse);

    default List<CategoryResponse> toResponseList(List<Category> CategoryList, List<Category> descriptionList) {
        return CategoryList.stream()
                .map(this::toResponseList).toList();
    }
}
