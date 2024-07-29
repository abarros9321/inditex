package com.prueba.inditex.mapper;

import com.prueba.inditex.persistence.entity.Price;
import com.prueba.inditex.service.dto.PriceDtoResponse;
import org.springframework.stereotype.Service;

@Service
public class PriceMapperService {

    public String formatPriceValue(Double priceValue, String currency) {
        return String.format("%.2f %S", priceValue, currency);
    }


    public PriceDtoResponse mapToDto(Price price) {
        PriceDtoResponse dto = new PriceDtoResponse();
        dto.setPriceList(price.getPriceList());
        dto.setPriceValue(formatPriceValue(price.getPriceValue(), price.getCurrency()));
        dto.setBrandId(price.getBrandId());
        dto.setProductId(price.getProductId());
        dto.setStartDate(price.getStartDate());
        dto.setEndDate(price.getEndDate());
        return dto;
    }
}
