package com.soumyajit.Order.Service.DTOS;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soumyajit.Order.Service.Entity.Enums.OrderStatus;
import com.soumyajit.Order.Service.Entity.OrdersItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {
    private Long id;
    private OrderStatus orderStatus;
    private Double totalPrice;
    @JsonIgnore
    private List<OrdersItem> items;
}
