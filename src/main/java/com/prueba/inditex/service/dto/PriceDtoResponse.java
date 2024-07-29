package com.prueba.inditex.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceDtoResponse {
    private Long productId;
    private Long brandId;
    private String priceValue;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priceList;
}
