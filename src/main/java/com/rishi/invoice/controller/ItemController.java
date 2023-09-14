package com.rishi.invoice.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rishi.invoice.model.Item;
import com.rishi.invoice.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@GetMapping("/")
	public ResponseEntity<List<Item>> getAllItems() {
		List<Item> items = itemService.getAllItems();
		return new ResponseEntity<>(items, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<Item> itemOptional = itemService.findById(id);
		
		if (itemOptional.isPresent()) {
	        // Item found, return it with HTTP status 200 OK
	        Item item = itemOptional.get();
	        return new ResponseEntity<>(item, HttpStatus.OK);
	    } else {
	        // Item not found, return a custom message with 404 Not Found
	        String errorMessage = "Item not found for ID: " + id;
	        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	    }
	}
	
	@PostMapping("/save")
    public Long saveItem(@RequestBody Item item) {
        return itemService.saveItem(item);
    }
	
	@GetMapping("/search")
	public List<Item> searchItems(@RequestParam String query) {
        List<Item> matchingItems = itemService.searchItems(query);
        return matchingItems;
        // Extract the item names and return as a list of strings
       // return matchingItems.stream().map(Item::getItemName).collect(Collectors.toList());
    }
	
	
}
