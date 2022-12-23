package com.kodlamaio.bootcampproject.business.requests.blacklists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBlackListRequest {
    private LocalDate date;
    @Size(min = 3,max = 254)
    @NotBlank
    private String reason;
    @Min(value = 1)
    private int applicantId;
}
