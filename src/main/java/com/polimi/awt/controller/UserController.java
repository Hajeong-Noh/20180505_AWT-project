package com.polimi.awt.controller;

import com.polimi.awt.model.User;
import com.polimi.awt.payload.SignUpRequest;
import com.polimi.awt.repository.RoleRepository;
import com.polimi.awt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/users")
    private User createUser(@RequestBody SignUpRequest signUpRequest){

        User newUser = new User (signUpRequest.getUsername(), signUpRequest.getPassword(), signUpRequest.getEmailAddress());

        newUser.addRole(roleRepository.findByName(signUpRequest.roleToRoleName()));
        return userRepository.save(newUser);
    }
}
