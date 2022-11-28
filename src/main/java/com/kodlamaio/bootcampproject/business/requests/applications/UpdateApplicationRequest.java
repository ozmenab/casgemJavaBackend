package com.kodlamaio.bootcampproject.business.requests.applications;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateApplicationRequest {
    private int id;
    @Min(value = 1)
    private int bootcampId;
    @Min(value = 1)
    private int userId;
    @Min(value = 1)
    private int state;
}
