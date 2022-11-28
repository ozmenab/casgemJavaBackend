package com.kodlamaio.bootcampproject.dataAccess.abstracts.users;

import com.kodlamaio.bootcampproject.entities.users.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor,Integer> {
    Instructor findByNationalIdentity(String nationalIdentity);
}
