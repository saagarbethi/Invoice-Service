package com.rishi.invoice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishi.invoice.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
	List<Item> findByItemNameContainingIgnoreCase(String query);
}
