package com.prueba.inditex.service;


import com.prueba.inditex.mapper.PriceToPriceDtoResponse;
import com.prueba.inditex.persistence.entity.Price;
import com.prueba.inditex.persistence.repository.PriceRepository;
import com.prueba.inditex.service.dto.PriceDtoResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;


@Service
@Qualifier("pricesServices")
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final PriceToPriceDtoResponse priceDtoResponse;
    private static final DateTimeFormatter format = new DateTimeFormatterBuilder().parseCaseInsensitive()
            .append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toFormatter();

    public PriceServiceImpl(PriceRepository priceRepository, PriceToPriceDtoResponse priceDtoResponse) {
        this.priceRepository = priceRepository;
        this.priceDtoResponse = priceDtoResponse;
    }

    public LocalDateTime fromStringToDate(String date) {
        return LocalDateTime.parse(date, format);
    }

    @Override
    public PriceDtoResponse findPrices(Long productId, Long brandId, String date) {
        return priceDtoResponse.map(priceRepository.getPrices(productId, brandId, fromStringToDate(date)).orElse(new Price()));
    }

}
