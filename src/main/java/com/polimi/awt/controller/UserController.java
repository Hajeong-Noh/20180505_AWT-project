package com.polimi.awt.controller;

import com.polimi.awt.exception.PreconditionFailedException;
import com.polimi.awt.model.users.User;
import com.polimi.awt.payload.UpdateUserRequest;
import com.polimi.awt.repository.UserRepository;
import com.polimi.awt.security.CurrentUser;
import com.polimi.awt.security.UserPrincipal;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Api(description="Get and update User information", tags = "Annotations")
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
    @CrossOrigin(origins = "http://localhost:4200")
    private UpdateUserRequest updateUsername (@CurrentUser UserPrincipal principal, @RequestBody @Valid UpdateUserRequest update) {

        if (userRepository.existsByUsername(update.getUsername())) {

            if(!userRepository.findUserByUsername(update.getUsername())
                    .getId()
                    .equals(principal.getId())){
                throw new PreconditionFailedException("Username already exists.");
            }
        }
        User user = userRepository.findUserById(principal.getId());
        user.updateInformation(update.getUsername(), update.getEmail(), passwordEncoder.encode(update.getPassword()));
        userRepository.save(user);
        return update;
    }

}
