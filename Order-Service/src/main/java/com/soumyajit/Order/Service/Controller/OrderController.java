package com.soumyajit.Order.Service.Controller;

import com.soumyajit.Order.Service.DTOS.OrderRequestDTO;
import com.soumyajit.Order.Service.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderRequestDTO>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderRequestDTO> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}
