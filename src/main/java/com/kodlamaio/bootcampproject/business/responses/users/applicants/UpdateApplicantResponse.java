package com.kodlamaio.bootcampproject.business.responses.users.applicants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateApplicantResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String about;
    private String nationalIdentity;
}
