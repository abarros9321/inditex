package com.prueba.inditex.service;


import com.prueba.inditex.service.dto.PriceDtoResponse;


public interface PriceService {
    PriceDtoResponse findPrices(Long productId, Long brandId, String date);
}
