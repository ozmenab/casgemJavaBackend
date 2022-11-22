package com.kodlamaio.bootcampproject.entities.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="instructors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instructor extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="companyName")
    private String companyName;
    @OneToOne
    @MapsId
    private User user;
}
