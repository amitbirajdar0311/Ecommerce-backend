package com.cts.service.customer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.entity.User;
import com.cts.repository.UserRegisterRepo;
import com.cts.service.customer.UserRegisterService;
@Service
public class UserRegisterServiceImpl implements UserRegisterService{

	@Autowired
	private UserRegisterRepo userRegisterRepo;
	@Override
	public User registerUser(User user) {
		
		return userRegisterRepo.save(user);
	}

}
