package com.soumyajit.Inventory.Service.Controller;

import com.soumyajit.Inventory.Service.DTOS.ProductDTO;
import com.soumyajit.Inventory.Service.Service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    @GetMapping("/fetching")
    public Object fetchingFromOrderService(){
        ServiceInstance orderService = discoveryClient
                .getInstances("Order-Service").getFirst();

        return restClient.get()
                .uri(orderService.getUri()+"/api/v1/orders/hello")
                .retrieve()
                .body(String.class);
    }



    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAllInventory(){
        return ResponseEntity.ok(productService.getAllInventory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

}
