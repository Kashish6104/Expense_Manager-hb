package com.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "Expenses")
@Data
public class ExpensesEntity {

	
    public enum Status {
        PENDING, COMPLETED, CANCELLED
    }
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    private String title;

    private Long categoryId;

    private Long subcategoryId;

    private Long vendorId;

    private Long accountId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private BigDecimal amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date transactionDate;

    private String description;

    private Long userId;

 

    
    
}