package com.kodlamaio.bootcampproject.entities.users;

import com.kodlamaio.bootcampproject.entities.Bootcamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="instructors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instructor extends User{

    @Column(name="companyName")
    private String companyName;

    @OneToMany(mappedBy = "instructor")
    private List<Bootcamp> bootcamp;

}
