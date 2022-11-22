package com.kodlamaio.bootcampproject.entities.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="applicants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Applicant extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="about")
    private String about;
    @OneToOne
    @MapsId
    private User user;
}
