package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entity.User;

public interface UserRegisterRepo extends JpaRepository<User, Long>{

}
