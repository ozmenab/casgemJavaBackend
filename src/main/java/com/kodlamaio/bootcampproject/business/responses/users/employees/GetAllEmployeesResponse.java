package com.kodlamaio.bootcampproject.business.responses.users.employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllEmployeesResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private String nationalIdentity;
}
