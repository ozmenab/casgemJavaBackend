package com.kodlamaio.bootcampproject.api.controllers;

import com.kodlamaio.bootcampproject.business.requests.users.UserLoginRequest;
import com.kodlamaio.bootcampproject.business.requests.users.UserRegisterRequest;
import com.kodlamaio.bootcampproject.dataAccess.abstracts.users.UserRepository;
import com.kodlamaio.bootcampproject.entities.users.User;
import com.kodlamaio.bootcampproject.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest userLoginRequest){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(),userLoginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtTokenProvider.generateJwtToken(authentication);
        return "Bearer "+jwtToken;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest userRegisterRequest){
        User user = userRepository.findByUsername(userRegisterRequest.getEmail());
        if(user != null)
            return ResponseEntity.badRequest().body("Username already exists");
        User newUser = new User();
        newUser.setEmail(userRegisterRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        userRepository.save(newUser);
        return new ResponseEntity<>("User successfully register", HttpStatus.CREATED);
    }
}
