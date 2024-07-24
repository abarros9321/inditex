package com.prueba.inditex.controller;

import com.prueba.inditex.service.PriceService;
import com.prueba.inditex.service.dto.PriceDtoResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/price")
public class PriceController {

    @Qualifier("pricesServices")
    private final PriceService pricesServices;

    public PriceController(PriceService pricesServices) {
        this.pricesServices = pricesServices;
    }



    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found price"),
            @ApiResponse(code = 404, message = "Price Not Found"),

    })
    @GetMapping("/{productId}/{brandId}/{date}")
    public ResponseEntity<PriceDtoResponse> getPrice(@ApiParam(
            name =  "productId",
            type = "Long",
            value = "Product Id",
            example = "35455",
            required = true)@PathVariable("productId") Long productId, @ApiParam(
            name =  "brandId",
            type = "Long",
            value = "brand Id",
            example = "1",
            required = true)@PathVariable("brandId") Long brandId,@ApiParam(
            name =  "date",
            type = "String",
            value = "date",
            example = "2020-06-14 16:00:00",
            required = true) @PathVariable("date") String date) {
        return new ResponseEntity<>(pricesServices.findPrices(productId, brandId, date), HttpStatus.OK);
    }



}
