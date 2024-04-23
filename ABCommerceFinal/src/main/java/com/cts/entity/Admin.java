package com.cts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	@Column(nullable = false)
	@Size(min = 3, message = "Should be atleast 3 characters")
	private String adminName;
	@Column(nullable = false, unique = true)
	@Email
	private String adminEmail;
	@Column(nullable = false)
	@Size(min = 8, message = "Password Length must be atleast 8")
	private String password;
}