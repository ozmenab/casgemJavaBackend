package com.kodlamaio.bootcampproject.entities.users;

import com.kodlamaio.bootcampproject.entities.BlackList;
import com.kodlamaio.bootcampproject.entities.applications.Application;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="applicants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Applicant extends User{

    @Column(name="about")
    private String about;

    @OneToMany(mappedBy = "applicant")
    private List<Application> applications;

    @OneToMany(mappedBy = "applicant")
    private List<BlackList> blackLists;
}
