package com.prueba.inditex.mapper;

import com.prueba.inditex.persistence.entity.Price;
import com.prueba.inditex.service.dto.PriceDtoResponse;
import org.springframework.stereotype.Component;

@Component
public class PriceToPriceDtoResponse implements IMapper<Price, PriceDtoResponse>{

    private final PriceMapperService priceMapperService;

    public PriceToPriceDtoResponse(PriceMapperService priceMapperService) {
        this.priceMapperService = priceMapperService;
    }

    @Override
    public PriceDtoResponse map(Price in) {
        return priceMapperService.mapToDto(in);
    }
}
