package com.rishi.invoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rishi.invoice.model.InvoiceDetails;
import com.rishi.invoice.model.InvoiceItem;
import com.rishi.invoice.model.InvoiceTotal;
import com.rishi.invoice.service.InvoiceService;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@PostMapping("/save-details")
	public ResponseEntity<Long> saveInvoiceDetails(@RequestBody InvoiceDetails invoice) {
		List<InvoiceItem> invoice_items = invoice.getInvoiceItem();
		InvoiceTotal invoice_total = invoice.getInvoiceTotal();
		Long invoiceNumber = invoiceService.saveInvoiceDetails(invoice_items, invoice_total);
		return new ResponseEntity<>(invoiceNumber, HttpStatus.OK);
	}
}
