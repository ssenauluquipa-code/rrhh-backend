package com.rrhh.sistema.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	private Long id;
    private String username;
    private String email;
    private String password;
    private String role; // ADMIN, HR, EMPLOYEE

}
