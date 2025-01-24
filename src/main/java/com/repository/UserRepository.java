package com.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.UserEntity;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
	Optional<UserEntity> findByEmail(String email);
	Optional<UserEntity> findByToken(String token);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users SET otp = :otp WHERE email = :email", nativeQuery = true)
	void updateOtpByEmail(@Param("email") String email, @Param("otp") String otp);

	

}
