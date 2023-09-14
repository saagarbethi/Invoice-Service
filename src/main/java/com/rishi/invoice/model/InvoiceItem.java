package com.rishi.invoice.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "invoice_items")
public class InvoiceItem {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long itemId;
	private int qty;
	private double unitPrice;
	private double total;
	
	 @ManyToOne
	 @JoinColumn(name = "invoice_total_id")
	 private InvoiceTotal invoiceTotal;

}
