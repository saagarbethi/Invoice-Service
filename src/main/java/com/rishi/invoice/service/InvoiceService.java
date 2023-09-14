package com.rishi.invoice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishi.invoice.dao.InvoiceItemRepository;
import com.rishi.invoice.dao.InvoiceTotalRepository;
import com.rishi.invoice.model.InvoiceItem;
import com.rishi.invoice.model.InvoiceTotal;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceItemRepository itemRepository;

	@Autowired
	private InvoiceTotalRepository totalRepository;

	public Long saveInvoiceDetails(List<InvoiceItem> items, InvoiceTotal total) {
		InvoiceTotal invoice_total = saveInvoiceTotal(total);
		items.forEach(item -> {
			item.setInvoiceTotal(invoice_total); // Set the invoiceTotal for each item
		});
		saveInvoiceItem(items);
		return invoice_total.getId();
	}

	public Long saveInvoiceItem(Iterable<InvoiceItem> items) {
		List<InvoiceItem> savedItems = itemRepository.saveAll(items);
		List<Long> ids = savedItems.stream().map(InvoiceItem::getId).collect(Collectors.toList());
		return ids.get(0);
	}

	public InvoiceTotal saveInvoiceTotal(InvoiceTotal total) {
		InvoiceTotal invoice_total = totalRepository.save(total);
		return invoice_total;
	}

}
