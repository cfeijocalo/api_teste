package com.me.teste.api_teste.repository;

import com.me.teste.api_teste.model.table.Items;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items, Long> {

    Items findByDescription(String description);

}
