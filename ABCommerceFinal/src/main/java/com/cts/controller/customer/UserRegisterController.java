package com.cts.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.entity.User;
import com.cts.service.customer.UserRegisterService;



@RestController
@RequestMapping("api/customer")
public class UserRegisterController {

	@Autowired
	private UserRegisterService userRegisterService;
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		return ResponseEntity.ok(userRegisterService.registerUser(user));
		
		
	}
}
