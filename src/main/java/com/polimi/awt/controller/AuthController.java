package com.polimi.awt.controller;


import com.polimi.awt.exception.PreconditionFailedException;
import com.polimi.awt.model.RoleName;
import com.polimi.awt.model.users.Manager;
import com.polimi.awt.model.users.User;
import com.polimi.awt.model.users.Worker;
import com.polimi.awt.payload.JwtAuthenticationResponse;
import com.polimi.awt.payload.LoginRequest;
import com.polimi.awt.payload.SignUpRequest;
import com.polimi.awt.payload.httpResponseStatus.ApiResponse;
import com.polimi.awt.payload.httpResponseStatus.CreatedResponse;
import com.polimi.awt.repository.RoleRepository;
import com.polimi.awt.repository.UserRepository;
import com.polimi.awt.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
@Api(description="Signup and Signin", tags = "Auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ApiResponse registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new PreconditionFailedException("Username already exists.");
        }

        // Instantiating user instance depending on role
        User user;
        if (signUpRequest.hasRoleManager()) {
            user = new Manager(signUpRequest.getUsername(), signUpRequest.getPassword(),
                    signUpRequest.getEmail(), roleRepository.findByName(RoleName.MANAGER));
        }
        else {
            user = new Worker(signUpRequest.getUsername(), signUpRequest.getPassword(),
                    signUpRequest.getEmail(), roleRepository.findByName(RoleName.WORKER));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return new CreatedResponse("User registered successfully");
    }
}
