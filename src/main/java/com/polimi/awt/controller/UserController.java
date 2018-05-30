package com.polimi.awt.controller;

import com.polimi.awt.model.users.User;
import com.polimi.awt.payload.UpdateUserRequest;
import com.polimi.awt.repository.UserRepository;
import com.polimi.awt.security.CurrentUser;
import com.polimi.awt.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/me")
    private User getUser (@CurrentUser UserPrincipal currentUser) {

        return userRepository.findUserById(currentUser.getId());
    }

    @PutMapping("/me")
    private UpdateUserRequest updateUsername (@CurrentUser UserPrincipal principal, @RequestBody UpdateUserRequest update) {

        User user = userRepository.findUserById(principal.getId());
        user.setUsername(update.getUsername());
        user.setEmailAddress(update.getEmail());
        user.setPassword(passwordEncoder.encode(update.getPassword()));
        userRepository.save(user);
        return update;
    }

}
