package com.prueba.inditex.service;

import com.prueba.inditex.mapper.PriceToPriceDtoResponse;
import com.prueba.inditex.persistence.entity.Price;
import com.prueba.inditex.persistence.repository.PriceRepository;
import com.prueba.inditex.service.dto.PriceDtoResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;
    @Mock
    private PriceToPriceDtoResponse priceToPriceDtoResponse;

    @InjectMocks
    private PriceServiceImpl priceService;

    private Price price;
    private PriceDtoResponse priceDtoResponse;
    private DateTimeFormatter format;

    @BeforeEach
    void setUp() {
        format = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .toFormatter();

        price = price.builder()
                .productId(35455L)
                .priceValue(35.50)
                .brandId(1L)
                .currency("EUR")
                .priceList(1L)
                .priority(0)
                .startDate(LocalDateTime.parse("2020-06-14 00:00:00", format))
                .endDate(LocalDateTime.parse("2020-12-31 23:59:59", format))
                .build();

        priceDtoResponse = priceDtoResponse.builder()
                .productId(35455L)
                .priceValue("35.50 EUR")
                .brandId(1L)
                .priceList(1L)
                .startDate(LocalDateTime.parse("2020-06-14 00:00:00", format))
                .endDate(LocalDateTime.parse("2020-12-31 23:59:59", format))
                .build();
    }

    @DisplayName("Test to get price by his productId, brandId and date")
    @Test
    void whenGetPriceThenReturnDataCorrectTest() {
        BDDMockito.given(priceRepository.getPrices(35455L,1L, LocalDateTime.parse("2020-06-14 00:00:00",format))).willReturn(Optional.of(price));
        BDDMockito.given(priceToPriceDtoResponse.map(price)).willReturn(priceDtoResponse);

        PriceDtoResponse savedPrice = priceService.findPrices(35455L,1L,"2020-06-14 00:00:00");

        Assertions.assertThat(savedPrice).isNotNull();
        Assertions.assertThat(savedPrice.getProductId()).isEqualTo(priceDtoResponse.getProductId());
        Assertions.assertThat(savedPrice.getPriceValue()).isEqualTo(priceDtoResponse.getPriceValue());
        Assertions.assertThat(savedPrice.getBrandId()).isEqualTo(priceDtoResponse.getBrandId());
        Assertions.assertThat(savedPrice.getPriceList()).isEqualTo(priceDtoResponse.getPriceList());
        Assertions.assertThat(savedPrice.getStartDate()).isEqualTo(priceDtoResponse.getStartDate());
        Assertions.assertThat(savedPrice.getEndDate()).isEqualTo(priceDtoResponse.getEndDate());
    }
}
