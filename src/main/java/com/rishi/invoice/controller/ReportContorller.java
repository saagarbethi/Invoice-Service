package com.rishi.invoice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rishi.invoice.service.report.ReportBuilderService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ReportContorller {

	@Autowired
	ReportBuilderService reportBuilderService;

	@GetMapping(value = "/generate-report/{invoiceno}")

	public ResponseEntity<byte[]> generateReport(@PathVariable String invoiceno, HttpServletResponse response) throws Exception {

		String path = "D:\\Reports\\Invoice.jrxml";
		
		String query = "select  i.item_name, ii.qty, ii.unit_price, "
				+ "ii.total,it.total billtotal,it.bill_to, it.ship_to, "
				+ "it.id invoicenumber,it.shipping_charges,it.subtotal,it.subtotal_less_discount,"
				+ "it.tax_rate,it.total_tax,it.discount "
				+ "From items i "
				+ "inner join invoice_items ii on i.id = ii.item_id "
				+ "inner join invoice_totals it on ii.invoice_total_id = it.id " + "where it.id = '" + invoiceno + "'";
		byte[] pdfBytes = reportBuilderService.generateReport(path, query);

		HttpHeaders headers = new HttpHeaders();
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", String.format("inline; filename=%s", path.replace("jrxml", "pdf")));
		response.setContentLength(pdfBytes.length);

        // Write the PDF bytes to the response output stream
        response.getOutputStream().write(pdfBytes);
        response.getOutputStream().flush();

        return ResponseEntity.ok().build();
	}
}
