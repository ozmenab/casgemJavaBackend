package com.kodlamaio.bootcampproject.entities.applications;

import com.kodlamaio.bootcampproject.entities.Bootcamp;
import com.kodlamaio.bootcampproject.entities.users.Applicant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="state")
    private String state;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;
    @ManyToOne
    @JoinColumn(name = "bootcamp_id")
    private Bootcamp bootcamp;
}
