package com.kodlamaio.bootcampproject.business.responses.applications;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllApplicationsResponse {
    private int id;
    private int bootcampId;
    private int applicantId;
    private int state;
    private String bootcampName;
}
