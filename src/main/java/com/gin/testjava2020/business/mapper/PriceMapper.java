package com.gin.testjava2020.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gin.testjava2020.model.PriceResponseBody;
import com.gin.testjava2020.persistence.PriceEntity;

/**
 * The interface Price mapper.
 */
@Mapper(componentModel = "spring")
public interface PriceMapper {

    /**
     * Map price response body.
     *
     * @param driverEntity the driver entity
     * @return the price response body
     */
    @Mapping(target="productId", source="productId")
    @Mapping(target="brandId", source="brandId")
    @Mapping(target="price", source="priceAmount")
    @Mapping(target="startDate", source="startDate")
    @Mapping(target="endDate", source="endDate")
    @Mapping(target="finalPrice", source="priceAmount")
    PriceResponseBody map(PriceEntity driverEntity);

}
