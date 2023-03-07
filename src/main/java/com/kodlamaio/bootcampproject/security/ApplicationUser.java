package com.kodlamaio.bootcampproject.security;

import com.kodlamaio.bootcampproject.entities.users.Role;
import com.kodlamaio.bootcampproject.entities.users.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
public class ApplicationUser implements UserDetails {
    private int id;
    private String nationalIdentity;
    private String username;
    private String email;
    private String password;
    private Set<? extends GrantedAuthority> authorities;
    public ApplicationUser(int id ,String nationalIdentity,
                           String username, String email, String password, Set<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nationalIdentity = nationalIdentity;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static ApplicationUser create(User user){
        Set<GrantedAuthority> authorityList = new HashSet<>();
        for(Role role: user.getRoles()){
            authorityList.add(new SimpleGrantedAuthority(role.name()));
        }
        return new ApplicationUser(user.getId(), user.getNationalIdentity(),
                user.getUsername(), user.getEmail(), user.getPassword(), authorityList);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
