package com.jbohorquez.emazon_hexagonal.application.mapper;

import com.jbohorquez.emazon_hexagonal.application.dto.BrandResponse;
import com.jbohorquez.emazon_hexagonal.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static com.jbohorquez.emazon_hexagonal.constants.ValidationConstants.*;

@Mapper(componentModel = SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {Brand.class})
public interface BrandResponseMapper {

    @Mapping(target = BRAND_ID, source = ID)
    @Mapping(target = BRAND_NAME, source = NAME)
    @Mapping(target = BRAND_DESCRIPTION, source = DESCRIPTION)
    BrandResponse toBrResponseList(Brand Brand);
}
