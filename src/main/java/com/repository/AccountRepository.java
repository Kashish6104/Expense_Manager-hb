package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.AccountEntity;
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer>{

}
