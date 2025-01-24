package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.AccountEntity;
import com.entity.UserEntity;
import com.repository.UserRepository;

@Service
public class UserService {

	
	@Autowired
    UserRepository userRepository;
	
	 public void createUserWithAccounts(UserEntity user, List<AccountEntity> accounts) {
	        // Associate accounts with the user
	        for (AccountEntity account : accounts) {
	            account.setUser(user);
	        }

	      
	        userRepository.save(user);
	    }
}
