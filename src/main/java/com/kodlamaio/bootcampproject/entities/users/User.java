package com.kodlamaio.bootcampproject.entities.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn                //
    private Instructor instructor;

    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn                //
    private Employee employee;

    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn                //
    private Applicant applicant;
}
