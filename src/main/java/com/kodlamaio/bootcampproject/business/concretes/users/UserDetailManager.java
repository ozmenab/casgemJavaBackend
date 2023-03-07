package com.kodlamaio.bootcampproject.business.concretes.users;

import com.kodlamaio.bootcampproject.dataAccess.abstracts.users.UserRepository;
import com.kodlamaio.bootcampproject.entities.users.User;
import com.kodlamaio.bootcampproject.security.ApplicationUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailManager implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user==null)
            throw new UsernameNotFoundException("User could not found");
        return ApplicationUser.create(user);
    }
}
