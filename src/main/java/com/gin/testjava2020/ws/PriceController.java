package com.gin.testjava2020.ws;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gin.testjava2020.business.service.PriceService;
import com.gin.testjava2020.exception.ProductNotFoundException;
import com.gin.testjava2020.model.PriceResponseBody;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/price")
@Slf4j
public class PriceController {

    private PriceService priceService;

    @GetMapping(
        headers = {
            HttpHeaders.ACCEPT + "=" + MediaType.APPLICATION_JSON_VALUE
        })
    @ApiOperation(
        value = "Get price operation",
        tags = {
            "Get price operation"
        }, response = PriceResponseBody.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. The response contains the Product Price data."),
        @ApiResponse(code = 400, message = "BAD_REQUEST. The request data is not correct"),
        @ApiResponse(code = 404, message = "NOT_FOUND. Operation not found")
    })
    public ResponseEntity<PriceResponseBody> getPrice(
        @RequestParam(name = "applyDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date applyDate,
        @RequestParam(name = "productId") Long productId,
        @RequestParam(name = "brandId") Long brandId) throws ProductNotFoundException {

        final PriceResponseBody priceResponseBody;

        try {
            priceResponseBody = priceService.getPriceByApplydate(applyDate, productId, brandId);
        } catch (ProductNotFoundException e) {
            log.error(new StringBuilder().append("Product Not found. ProductId: ").append(productId).toString(), e);
            throw e;
        }

        return ResponseEntity.ok(priceResponseBody);
    }

}
