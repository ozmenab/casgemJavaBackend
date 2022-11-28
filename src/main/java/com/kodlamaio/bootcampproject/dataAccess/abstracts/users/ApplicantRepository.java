package com.kodlamaio.bootcampproject.dataAccess.abstracts.users;

import com.kodlamaio.bootcampproject.entities.users.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant,Integer> {
    Applicant findByNationalIdentity(String nationalIdentity);
}
