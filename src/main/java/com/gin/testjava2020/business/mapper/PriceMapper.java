package com.gin.testjava2020.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gin.testjava2020.model.PriceResponseBody;
import com.gin.testjava2020.persistence.Price;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(target="productId", source="productId")
    @Mapping(target="brandId", source="brandId")
    @Mapping(target="price", source="price")
    @Mapping(target="startDate", source="startDate")
    @Mapping(target="endDate", source="endDate")
    @Mapping(target="finalPrice", source="price")
    PriceResponseBody map(Price driverEntity);

}
