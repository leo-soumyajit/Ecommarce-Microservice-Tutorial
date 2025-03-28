package com.soumyajit.Inventory.Service.DTOS;

import lombok.Data;

@Data
public class OrderRequestItemDTOS {

    private Long productId;
    private Integer quantity;

}
