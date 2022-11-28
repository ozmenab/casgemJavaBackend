package com.kodlamaio.bootcampproject.business.requests.users.instructors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInstructorRequest {
    @Size(min = 3)
    @NotNull
    private String firstName;
    @Size(min = 3)
    @NotBlank
    private String lastName;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 3,message = "must be minimum 3 char")
    private String companyName;
    @NotBlank
    private String password;
    @Size(min = 11,max = 11)
    private String nationalIdentity;

}
