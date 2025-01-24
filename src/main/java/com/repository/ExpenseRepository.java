package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.ExpensesEntity;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpensesEntity, Long>{

	
}
