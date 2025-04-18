package com.soumyajit.Order.Service.Service;

import com.soumyajit.Order.Service.Clients.InventoryClient;
import com.soumyajit.Order.Service.DTOS.OrderRequestDTO;
import com.soumyajit.Order.Service.Entity.Enums.OrderStatus;
import com.soumyajit.Order.Service.Entity.Orders;
import com.soumyajit.Order.Service.Entity.OrdersItem;
import com.soumyajit.Order.Service.Repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final InventoryClient inventoryClient;

    public List<OrderRequestDTO> getAllOrders(){
        log.info("fetching all orders");
        List<Orders> orders = orderRepository.findAll();
        return orders.stream()
                .map(orders1 -> modelMapper.map(orders1, OrderRequestDTO.class))
                .collect(Collectors.toList());
    }

    public OrderRequestDTO getOrderById(Long id){
        log.info("Getting order with Id {} ",id);
        return orderRepository.findById(id)
                .map(orders -> modelMapper.map(orders, OrderRequestDTO.class))
                .orElseThrow(()->new RuntimeException("Order not found with Id : "+id));
    }


    @Transactional
    //@Retry(name="inventoryRetry",fallbackMethod = "createOrderFallbackMethod")
    @CircuitBreaker(name="inventoryCircuitBreaker",fallbackMethod = "createOrderFallbackMethod")
    //@RateLimiter(name = "inventoryRatelimiter",fallbackMethod = "createOrderFallbackMethod")
    public OrderRequestDTO createOrder(OrderRequestDTO orderRequestDTO) {
        log.info("Calling the createOrder method");
        Double totalPrice = inventoryClient.reduceStocks(orderRequestDTO);
        Orders orders = modelMapper.map(orderRequestDTO,Orders.class);
        for (OrdersItem ordersItem : orders.getItems()){
            ordersItem.setOrder(orders);
        }
        orders.setTotalPrice(totalPrice);
        orders.setOrderStatus(OrderStatus.CONFIRMED);
        Orders saveedOrders = orderRepository.save(orders);
        return modelMapper.map(orders,OrderRequestDTO.class);
    }

    public OrderRequestDTO createOrderFallbackMethod(OrderRequestDTO orderRequestDTO,Throwable throwable) {
        log.error("Fallback method {}",throwable.getLocalizedMessage());
        return new OrderRequestDTO();
    }
















}
