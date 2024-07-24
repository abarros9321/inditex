package com.prueba.inditex.service;

import com.prueba.inditex.exception.InditexException;
import com.prueba.inditex.mapper.PriceToPriceDtoResponse;
import com.prueba.inditex.persistence.entity.Price;
import com.prueba.inditex.persistence.repository.PriceRepository;
import com.prueba.inditex.service.dto.PriceDtoResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Optional;

@Service
@Qualifier("pricesServices")
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final PriceToPriceDtoResponse priceDtoResponse;

    public PriceServiceImpl(PriceRepository priceRepository, PriceToPriceDtoResponse priceDtoResponse) {
        this.priceRepository = priceRepository;
        this.priceDtoResponse = priceDtoResponse;
    }

    public LocalDateTime fromStringToDate(String date) {
        DateTimeFormatter format = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .toFormatter();

        return LocalDateTime.parse(date, format);
    }

    @Override
    public PriceDtoResponse findPrices(Long productId, Long brandId, String date) {

        Optional<Price> price = priceRepository.getPrices(productId, brandId, fromStringToDate(date));
        if (price.isEmpty()){
            throw new InditexException("Price Not Found", HttpStatus.NOT_FOUND);
        }
        return priceDtoResponse.map(price.get());
    }

}
