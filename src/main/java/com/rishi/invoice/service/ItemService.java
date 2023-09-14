package com.rishi.invoice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishi.invoice.dao.ItemRepository;
import com.rishi.invoice.model.Item;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public Long saveItem(Item item) {
		Item savedItem = itemRepository.save(item);
		return savedItem.getId();
	}
	
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}
	
	public Optional<Item> findById(Long id) {
		return itemRepository.findById(id);
	}
	
	 public List<Item> searchItems(String query) {
		 return itemRepository.findByItemNameContainingIgnoreCase(query);
	 }
}
