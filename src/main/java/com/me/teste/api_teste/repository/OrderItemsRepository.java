package com.me.teste.api_teste.repository;

import com.me.teste.api_teste.model.table.OrderItems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {

}
