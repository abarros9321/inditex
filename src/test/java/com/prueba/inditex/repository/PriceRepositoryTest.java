package com.prueba.inditex.repository;

import com.prueba.inditex.persistence.entity.Price;
import com.prueba.inditex.persistence.repository.PriceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Optional;

@DataJpaTest
class PriceRepositoryTest {
    @Autowired
    private PriceRepository priceRepository;

    private Price price;
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
    }

    @DisplayName("Test to Create a price")
    @Test
    void whenCreatePriceThenReturnDataCorrectTest() {
        Price savedPrice = priceRepository.save(price);
        Assertions.assertThat(savedPrice).isNotNull();
        Assertions.assertThat(savedPrice.getProductId()).isEqualTo(price.getProductId());
        Assertions.assertThat(savedPrice.getPriceValue()).isEqualTo(price.getPriceValue());
        Assertions.assertThat(savedPrice.getBrandId()).isEqualTo(price.getBrandId());
        Assertions.assertThat(savedPrice.getCurrency()).isEqualTo(price.getCurrency());
        Assertions.assertThat(savedPrice.getPriceList()).isEqualTo(price.getPriceList());
        Assertions.assertThat(savedPrice.getPriority()).isEqualTo(price.getPriority());
        Assertions.assertThat(savedPrice.getStartDate()).isEqualTo(price.getStartDate());
        Assertions.assertThat(savedPrice.getEndDate()).isEqualTo(price.getEndDate());
    }

    @DisplayName("to get a price By his productId, brandId and date")
    @Test
    void whenGetPriceThenReturnDataCorrectTest() {
        priceRepository.save(price);
        Optional<Price> priceFinded = priceRepository.getPrices(35455L,1L, LocalDateTime.parse("2020-06-14 00:00:00",format));

        Assertions.assertThat(priceFinded).isNotNull();
        Assertions.assertThat(priceFinded.orElseThrow().getId()).isNotNull();
    }
}
