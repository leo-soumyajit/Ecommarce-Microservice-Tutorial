package com.soumyajit.Inventory.Service.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "Order-Service",path = "/orders")
public interface OrderClient {

    @GetMapping("/core/hello")
    public String getHelloOrders();
}
