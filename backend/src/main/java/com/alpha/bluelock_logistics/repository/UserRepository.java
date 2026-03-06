package com.alpha.bluelock_logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.bluelock_logistics.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
}
