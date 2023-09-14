package com.rishi.invoice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishi.invoice.model.InvoiceItem;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {

}
