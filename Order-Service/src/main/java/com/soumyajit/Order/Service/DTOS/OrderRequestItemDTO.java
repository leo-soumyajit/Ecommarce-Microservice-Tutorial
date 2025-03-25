package com.soumyajit.Order.Service.DTOS;

import lombok.Data;

@Data
public class OrderRequestItemDTO {
    private Long id;
    private Long productId;
    private Integer quantity;
}
