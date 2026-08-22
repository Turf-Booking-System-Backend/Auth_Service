package com.faizan.turfbooking.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.faizan.turfbooking.authservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);
//<entity Classname, primarykeytype>

	Optional<User> findByEmail(String email);
}
