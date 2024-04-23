package com.cts.dto;


import com.cts.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private Long id;
	private String email;
	private String name;
	private UserRole userRole;
}
