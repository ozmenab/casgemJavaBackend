package com.kodlamaio.bootcampproject.entities;

import com.kodlamaio.bootcampproject.entities.users.Applicant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="blacklists")
public class BlackList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="date")
    private LocalDate date;

    @Column(name="reason")
    private String reason;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;
}
