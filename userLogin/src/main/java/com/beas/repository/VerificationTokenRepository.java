package com.beas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beas.entities.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

	VerificationToken findByToken(String token);

}
