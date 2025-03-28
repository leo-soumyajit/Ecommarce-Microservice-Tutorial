package com.soumyajit.Order.Service.Clients;

import com.soumyajit.Order.Service.DTOS.OrderRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Inventory-Service",path = "/inventory")
public interface InventoryClient {

    @PutMapping("/products/reduce-stock")
    public Double reduceStocks(@RequestBody OrderRequestDTO orderRequestDTO);
}
