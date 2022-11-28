package com.kodlamaio.bootcampproject.business.responses.bootcamps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBootcampResponse {
    private int id;
    private int instructorId;
    private String instructorFirstName;
    private String instructorLastName;
    private String name;
    private int state;
    private LocalDate startDate;
    private LocalDate endDate;

}
