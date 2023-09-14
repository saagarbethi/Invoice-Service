package com.rishi.invoice.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "invoice_totals")
public class InvoiceTotal {
	
	@Id
	@GenericGenerator(
	        name = "invoice-sequence-generator",
	        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	        parameters = {
	                @Parameter(name = "sequence_name", value = "invoice_sequence"),
	                @Parameter(name = "initial_value", value = "100000"),
	                @Parameter(name = "increment_size", value = "1")
	        }
	)
	@GeneratedValue(generator = "invoice-sequence-generator")
    private Long id;

    private double subtotal;
    private double discount;
    private double subtotalLessDiscount;
    private double taxRate;
    private double totalTax;
    private double shippingCharges;
    private double total;
    
    private String billTo;
    private String shipTo;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdon;

    @PrePersist
    private void onCreate() {
    	createdon = new Date();
    }
}
