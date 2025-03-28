package com.soumyajit.Inventory.Service.Service;

import com.soumyajit.Inventory.Service.DTOS.OrderRequestDTO;
import com.soumyajit.Inventory.Service.DTOS.OrderRequestItemDTOS;
import com.soumyajit.Inventory.Service.DTOS.ProductDTO;
import com.soumyajit.Inventory.Service.Entities.Product;
import com.soumyajit.Inventory.Service.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public List<ProductDTO> getAllInventory(){
        log.info("Fetching all inventory items");
        List<Product> inventories = productRepository.findAll();
        return inventories.stream()
                .map(inventory-> modelMapper.map(inventory, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id){
        log.info("Getting product with Id {}",id);
        return productRepository
                .findById(id)
                .map(product -> modelMapper.map(product,ProductDTO.class))
                .orElseThrow(()-> new RuntimeException("Inventory  Not found"));
    }

    @Transactional
    public Double reduceStocks(OrderRequestDTO orderRequestDto) {
        log.info("Reducing the stocks");
        Double totalPrice = 0.0;
        for(OrderRequestItemDTOS orderRequestItemDto: orderRequestDto.getItems()) {
            Long productId = orderRequestItemDto.getProductId();
            Integer quantity = orderRequestItemDto.getQuantity();

            Product product = productRepository.findById(productId).orElseThrow(() ->
                    new RuntimeException("Product not found with id: "+productId));

            if(product.getStock() < quantity) {
                throw new RuntimeException("Product cannot be fulfilled for given quantity");
            }

            product.setStock(product.getStock()-quantity);
            productRepository.save(product);
            totalPrice += quantity*product.getPrice();
        }
        return totalPrice;
    }
}
