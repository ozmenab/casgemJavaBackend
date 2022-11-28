package com.kodlamaio.bootcampproject.business.responses.blacklists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBlackListResponse {
    private int id;
    private LocalDate date;
    private String reason;
    private int applicantId;
}
