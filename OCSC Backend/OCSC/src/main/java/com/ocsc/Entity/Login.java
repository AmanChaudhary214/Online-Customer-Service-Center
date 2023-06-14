package com.ocsc.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {

	private Integer username;
	private String password;
	private UserType type;
	private Boolean isActive;
	
}
