package com.kodlamaio.bootcampproject.dataAccess.abstracts.applications;

import com.kodlamaio.bootcampproject.entities.applications.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application,Integer> {
}
