package com.rishi.invoice.model;

import java.util.List;

import lombok.Data;

@Data
public class InvoiceDetails {

	private List<InvoiceItem> invoiceItem;
	private InvoiceTotal invoiceTotal;
}
