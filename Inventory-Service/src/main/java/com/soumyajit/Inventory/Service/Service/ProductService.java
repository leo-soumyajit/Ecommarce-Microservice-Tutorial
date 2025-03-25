package com.soumyajit.Inventory.Service.Service;

import com.soumyajit.Inventory.Service.DTOS.ProductDTO;
import com.soumyajit.Inventory.Service.Entities.Product;
import com.soumyajit.Inventory.Service.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

}
