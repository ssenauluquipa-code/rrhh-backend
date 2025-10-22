package com.rrhh.sistema.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Employee {

    private Integer employeeId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationality;
    private String documentId;
    private String phone;
    private String email;
    private String address;
    private String photo;
    private Boolean status = true;
    private LocalDate hireDate;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
