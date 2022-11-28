package com.kodlamaio.bootcampproject.business.requests.users.employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequest {
    @Min(value = 1)
    private int id;
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
