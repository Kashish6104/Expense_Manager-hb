package com.entity;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "vendor")
@Data
public class VendorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "vendorId")
    private UUID vendorId;

    @Column(name = "title", nullable = false)
    private String title;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId") 
    private UserEntity user;

}
