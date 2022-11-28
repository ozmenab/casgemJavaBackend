package com.kodlamaio.bootcampproject.dataAccess.abstracts;

import com.kodlamaio.bootcampproject.entities.Bootcamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BootcampRepository extends JpaRepository<Bootcamp,Integer> {
}
