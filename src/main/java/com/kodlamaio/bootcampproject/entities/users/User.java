package com.kodlamaio.bootcampproject.entities.users;

import com.kodlamaio.bootcampproject.entities.applications.Application;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="dateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name="nationalIdentity")
    private String nationalIdentity;

    @Enumerated(EnumType.STRING)
    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles = Set.of(Role.ROLE_USER);

}
