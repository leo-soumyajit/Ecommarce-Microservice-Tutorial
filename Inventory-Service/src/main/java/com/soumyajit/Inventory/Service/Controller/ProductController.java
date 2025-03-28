package com.soumyajit.Inventory.Service.Controller;

import com.soumyajit.Inventory.Service.Clients.OrderClient;
import com.soumyajit.Inventory.Service.DTOS.OrderRequestDTO;
import com.soumyajit.Inventory.Service.DTOS.ProductDTO;
import com.soumyajit.Inventory.Service.Service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Order;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    private final DiscoveryClient discoveryClient;
//    private final RestClient restClient;
    private final OrderClient orderClient;

    //with rest client
//    @GetMapping("/fetching")
//    public Object fetchingFromOrderService(){
//        ServiceInstance orderService = discoveryClient
//                .getInstances("Order-Service").getFirst();
//
//        return restClient.get()
//                .uri(orderService.getUri()+"/orders/core/hello")
//                .retrieve()
//                .body(String.class);
//    }

    @GetMapping("/fetching")
    public Object getHelloOrders(){
        return orderClient.getHelloOrders();
    }



    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAllInventory(){
        return ResponseEntity.ok(productService.getAllInventory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/reduce-stock")
    public ResponseEntity<Double> reduceStock(@RequestBody OrderRequestDTO orderRequestDTO){
        return ResponseEntity.ok(productService.reduceStocks(orderRequestDTO));
    }

}
