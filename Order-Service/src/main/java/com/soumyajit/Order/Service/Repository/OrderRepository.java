package com.soumyajit.Order.Service.Repository;

import com.soumyajit.Order.Service.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Long> {
}
