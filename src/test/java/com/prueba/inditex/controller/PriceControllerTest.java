package com.prueba.inditex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.inditex.persistence.entity.Price;
import com.prueba.inditex.service.PriceService;
import com.prueba.inditex.service.PriceServiceImpl;
import com.prueba.inditex.service.dto.PriceDtoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(controllers = {PriceController.class})
class PriceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PriceServiceImpl priceService;


    private PriceDtoResponse priceDtoResponse;
    private PriceDtoResponse priceDtoResponse2;
    private PriceDtoResponse priceDtoResponse3;
    private PriceDtoResponse priceDtoResponse4;
    private DateTimeFormatter format;

    @BeforeEach
    void setUp() {
        format = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .toFormatter();


        priceDtoResponse = priceDtoResponse.builder()
                .productId(35455L)
                .priceValue("35.50 EUR")
                .brandId(1L)
                .priceList(1L)
                .startDate(LocalDateTime.parse("2020-06-14 00:00:00", format))
                .endDate(LocalDateTime.parse("2020-12-31 23:59:59", format))
                .build();
        priceDtoResponse2 = priceDtoResponse2.builder()
                .productId(35455L)
                .priceValue("25.45 EUR")
                .brandId(1L)
                .priceList(2L)
                .startDate(LocalDateTime.parse("2020-06-14 15:00:00", format))
                .endDate(LocalDateTime.parse("2020-06-14 18:30:00", format))
                .build();
        priceDtoResponse3 = priceDtoResponse3.builder()
                .productId(35455L)
                .priceValue("35.50 EUR")
                .brandId(1L)
                .priceList(3L)
                .startDate(LocalDateTime.parse("2020-06-15 00:00:00", format))
                .endDate(LocalDateTime.parse("2020-06-15 11:00:00", format))
                .build();
        priceDtoResponse4 = priceDtoResponse4.builder()
                .productId(35455L)
                .priceValue("38.95 EUR")
                .brandId(1L)
                .priceList(4L)
                .startDate(LocalDateTime.parse("2020-06-15 16:00:00", format))
                .endDate(LocalDateTime.parse("2020-12-31 23:59:59", format))
                .build();
    }

    @DisplayName("Test 1 to get a price by productId = 35455, brandId = 1, date = \"2020-06-14 10:00:00\"")
    @Test
    void WhenGetPriceFirstCaseTest() throws Exception {
        BDDMockito.given(priceService.findPrices(35455L, 1L, "2020-06-14 10:00:00")).willReturn((priceDtoResponse));

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/price/{productId}/{brandId}/{date}", 35455, 1, "2020-06-14 10:00:00"));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId", is(35455)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceValue", is("35.50 EUR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate", is("2020-06-14T00:00:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate", is("2020-12-31T23:59:59")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList", is(1)));
    }

    @DisplayName("Test 2 to get a price by productId = 35455, brandId = 1, date = \"2020-06-14 16:00:00\"")
    @Test
    void WhenGetPriceSecondCaseTest() throws Exception {
        BDDMockito.given(priceService.findPrices(35455L, 1L, "2020-06-14 16:00:00")).willReturn((priceDtoResponse2));

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/price/{productId}/{brandId}/{date}", 35455, 1, "2020-06-14 16:00:00"));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId", is(35455)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceValue", is("25.45 EUR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate", is("2020-06-14T15:00:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate", is("2020-06-14T18:30:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList", is(2)));
    }

    @DisplayName("Test 3 to get a price by productId = 35455, brandId = 1, date = \"2020-06-14 21:00:00\"")
    @Test
    void WhenGetPriceThirdCaseTest() throws Exception {
        BDDMockito.given(priceService.findPrices(35455L, 1L, "2020-06-14 21:00:00")).willReturn((priceDtoResponse));

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/price/{productId}/{brandId}/{date}", 35455, 1, "2020-06-14 21:00:00"));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId", is(35455)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceValue", is("35.50 EUR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate", is("2020-06-14T00:00:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate", is("2020-12-31T23:59:59")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList", is(1)));
    }

    @DisplayName("Test 4 to get a price by productId = 35455, brandId = 1, date = \"2020-06-15 10:00:00\"")
    @Test
    void WhenGetPriceFourthCaseTest() throws Exception {
        BDDMockito.given(priceService.findPrices(35455L, 1L, "2020-06-15 10:00:00")).willReturn((priceDtoResponse3));

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/price/{productId}/{brandId}/{date}", 35455, 1, "2020-06-15 10:00:00"));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId", is(35455)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceValue", is("35.50 EUR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate", is("2020-06-15T00:00:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate", is("2020-06-15T11:00:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList", is(3)));
    }

    @DisplayName("Test 5 to get a price by productId = 35455, brandId = 1, date = \"2020-06-16 21:00:00\"")
    @Test
    void WhenGetPriceFifthCaseTest() throws Exception {
        BDDMockito.given(priceService.findPrices(35455L, 1L, "2020-06-16 21:00:00")).willReturn((priceDtoResponse4));

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/price/{productId}/{brandId}/{date}", 35455, 1, "2020-06-16 21:00:00"));

        response.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId", is(35455)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceValue", is("38.95 EUR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate", is("2020-06-15T16:00:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate", is("2020-12-31T23:59:59")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList", is(4)));
    }

}
