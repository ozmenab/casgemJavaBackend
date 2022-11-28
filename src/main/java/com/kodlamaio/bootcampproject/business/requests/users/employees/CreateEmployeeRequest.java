package com.kodlamaio.bootcampproject.business.requests.users.employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequest {
    @NotBlank
    @Length(min = 1, max =254 )
    private String firstName;
    @NotBlank
    @Length(min = 1, max = 254)
    private String lastName;
    @NotNull
    private LocalDate dateOfBirth;
    @NotBlank
    @Size(min=11,max=11)
    private String nationalIdentity;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @Size(min=3, max = 254)
    @NotBlank
    private String position;
}
