package com.kodlamaio.bootcampproject.business.requests.applications;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateApplicationRequest {
    @Min(value = 1)
    private int bootcampId;
    @Min(value = 1)
    private int applicantId;
    @Min(value = 1)
    private int state;
}
