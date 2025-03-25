package com.soumyajit.Order.Service.Entity;

import com.soumyajit.Order.Service.Entity.Enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Entity
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    private Double totalPrice;

    @OneToMany(mappedBy = "order", cascade=CascadeType.ALL,orphanRemoval = true)
    private List<OrdersItem> items;

}
