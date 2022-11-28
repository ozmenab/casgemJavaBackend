package com.kodlamaio.bootcampproject.business.responses.users.applicants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllApplicantsResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String about;
    private String nationalIdentity;
}
