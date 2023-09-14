package com.rishi.invoice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishi.invoice.model.InvoiceTotal;

public interface InvoiceTotalRepository extends JpaRepository<InvoiceTotal, Long> {

}
