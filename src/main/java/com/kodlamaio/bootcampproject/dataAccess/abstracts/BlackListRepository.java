package com.kodlamaio.bootcampproject.dataAccess.abstracts;

import com.kodlamaio.bootcampproject.entities.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList,Integer> {
    BlackList findByApplicantId(int id);
}
