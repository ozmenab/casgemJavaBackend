package com.kodlamaio.bootcampproject.dataAccess.abstracts.users;

import com.kodlamaio.bootcampproject.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
}
