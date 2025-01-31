package com.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
	
	
	 public enum Role {
	        ADMIN,
	        USER
	    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "userId", updatable = false, nullable = false)
    private UUID userId;
 
    
    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "active", nullable = false)
    private boolean active;

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "otp")
    private String otp;
    
    @Column(name = "token")
    private String token;
    
    
   
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AccountEntity> accounts;
    
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VendorEntity> vendors;
    
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CategoryEntity> categories;
    
    
    
    
    
//    public void addAccount(AccountEntity account) {
//        accounts.add(account);
//        account.setUser(this);
//    }
//
//    public void removeAccount(AccountEntity account) {
//        accounts.remove(account);
//        account.setUser(null);
//    }

}
