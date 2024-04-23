package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {

	List<Login> findByEmail(String email);
}
