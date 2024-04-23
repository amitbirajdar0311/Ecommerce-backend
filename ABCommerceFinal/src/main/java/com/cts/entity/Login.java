package com.cts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

@Data

@ToString

@AllArgsConstructor

@NoArgsConstructor

public class Login {
 


		@Id

	    @GeneratedValue(strategy= GenerationType.IDENTITY)

	    private int id;

	    private String email;

	    private String password;

	    private String role;

	}
