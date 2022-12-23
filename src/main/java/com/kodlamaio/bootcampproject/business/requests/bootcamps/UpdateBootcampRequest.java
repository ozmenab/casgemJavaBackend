package com.kodlamaio.bootcampproject.business.requests.bootcamps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBootcampRequest {
    @Min(value = 1)
    private int instructorId;
    @NotBlank
    @Size(min = 3,max=254)
    private String name;
    @Min(1)
    private int state;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
}
