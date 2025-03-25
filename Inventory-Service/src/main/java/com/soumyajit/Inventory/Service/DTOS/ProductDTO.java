package com.soumyajit.Inventory.Service.DTOS;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String title;
    private Double price;
    private Integer stock;
}
