package com.prueba.inditex.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @DisplayName("Test 1 to get a price by productId = 35455, brandId = 1, date = \"2020-06-14 10:00:00\"")
    @Test
    void WhenGetPriceFirstCaseTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/price/{productId}/{brandId}/{date}", 35455, 1, "2020-06-14 10:00:00"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId", is(35455)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceValue", is("35,50 EUR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate", is("2020-06-14T00:00:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate", is("2020-12-31T23:59:59")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList", is(1)));
    }

    @DisplayName("Test 2 to get a price by productId = 35455, brandId = 1, date = \"2020-06-14 16:00:00\"")
    @Test
    void WhenGetPriceSecondCaseTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price/{productId}/{brandId}/{date}", 35455, 1, "2020-06-14 16:00:00"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId", is(35455)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceValue", is("24,45 EUR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate", is("2020-06-14T15:00:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate", is("2020-06-14T18:30:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList", is(2)));
    }

    @DisplayName("Test 3 to get a price by productId = 35455, brandId = 1, date = \"2020-06-14 21:00:00\"")
    @Test
    void WhenGetPriceThirdCaseTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price/{productId}/{brandId}/{date}", 35455, 1, "2020-06-14 21:00:00"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId", is(35455)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceValue", is("35,50 EUR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate", is("2020-06-14T00:00:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate", is("2020-12-31T23:59:59")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList", is(1)));
    }

    @DisplayName("Test 4 to get a price by productId = 35455, brandId = 1, date = \"2020-06-15 10:00:00\"")
    @Test
    void WhenGetPriceFourthCaseTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price/{productId}/{brandId}/{date}", 35455, 1, "2020-06-15 10:00:00"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId", is(35455)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceValue", is("30,50 EUR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate", is("2020-06-15T00:00:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate", is("2020-06-15T11:00:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList", is(3)));
    }

    @DisplayName("Test 5 to get a price by productId = 35455, brandId = 1, date = \"2020-06-16 21:00:00\"")
    @Test
    void WhenGetPriceFifthCaseTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price/{productId}/{brandId}/{date}", 35455, 1, "2020-06-16 21:00:00"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId", is(35455)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceValue", is("38,95 EUR")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate", is("2020-06-15T16:00:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate", is("2020-12-31T23:59:59")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList", is(4)));
    }

    @DisplayName("Test get a price by productId = 1, brandId = 1, date = \"2020-06-16 21:00:00\" and recibe a 404 error")
    @Test
    void WhenGetPriceAndGetANotFoundErrorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price/{productId}/{brandId}/{date}", 1, 1, "2020-06-16 21:00:00"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @DisplayName("Test get a price by productId = 35455, brandId = 1, date = \"2020-06-1621:00:00\" and recibe a 400 error")
    @Test
    void WhenGetPriceFailTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/price/{productId}/{brandId}/{date}", 35455, 1, "2020-06-1621:00:00"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
