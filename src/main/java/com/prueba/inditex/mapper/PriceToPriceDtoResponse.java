package com.prueba.inditex.mapper;

import com.prueba.inditex.persistence.entity.Price;
import com.prueba.inditex.service.dto.PriceDtoResponse;
import org.springframework.stereotype.Component;

@Component
public class PriceToPriceDtoResponse implements IMapper<Price, PriceDtoResponse>{

    @Override
    public PriceDtoResponse map(Price in) {
        PriceDtoResponse dto = new PriceDtoResponse();
        dto.setPriceList(in.getPriceList());
        dto.setPriceValue(in.getPriceValue()+" "+in.getCurrency());
        dto.setBrandId(in.getBrandId());
        dto.setProductId(in.getProductId());
        dto.setStartDate(in.getStartDate());
        dto.setEndDate(in.getEndDate());
        return dto;
    }
}
