package com.soumyajit.Inventory.Service.Repository;

import com.soumyajit.Inventory.Service.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
