package com.gin.testjava2020.ws;


import com.gin.testjava2020.model.PriceResponseBody;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/price")
public class PriceController {

    @GetMapping(value = "${price.url} + ${price.urls.byApplyDate}",
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
        @RequestParam(name = "applyDate") String applyDate,
        @RequestParam(name = "productId") Long productId,
        @RequestParam(name = "brandId") Long brandId) {

        /*final PriceResponseBody result = priceService.getPriceByApplydate(applyDate, productId, brandId);
        //TODO: Log this and take the Exceptions
        return ResponseEntity.ok(result);*/
        return ResponseEntity.ok(new PriceResponseBody());
    }
}
