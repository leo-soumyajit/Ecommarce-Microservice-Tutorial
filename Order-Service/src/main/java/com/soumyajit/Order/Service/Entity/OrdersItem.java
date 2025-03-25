package com.soumyajit.Order.Service.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrdersItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Integer quantity;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Orders order;

}
