package com.polimi.awt.controller;

import com.polimi.awt.model.users.Manager;
import com.polimi.awt.model.users.User;
import com.polimi.awt.model.users.Worker;
import com.polimi.awt.payload.SignUpRequest;
import com.polimi.awt.repository.RoleRepository;
import com.polimi.awt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/users")
    private User createUser(@RequestBody SignUpRequest signUpRequest) {
        User newUser;

        if (signUpRequest.hasRoleManager()) {
            newUser = new Manager(signUpRequest.getUsername(), signUpRequest.getPassword(),
                    signUpRequest.getEmailAddress(), roleRepository.findByName(signUpRequest.roleToRoleName()));
        }
        else {
            newUser = new Worker(signUpRequest.getUsername(), signUpRequest.getPassword(),
                    signUpRequest.getEmailAddress(), roleRepository.findByName(signUpRequest.roleToRoleName()));
        }
        return userRepository.save(newUser);
    }

    @GetMapping("/users/{userId}")
    private Optional<User> getUser (@PathVariable Long userId) {

        return userRepository.findById(userId);
    }
}
