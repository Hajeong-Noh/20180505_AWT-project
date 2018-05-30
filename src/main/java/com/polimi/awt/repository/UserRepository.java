package com.polimi.awt.repository;


import com.polimi.awt.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    Optional<User> findByEmailAddress(String email);

    Optional<User> findByUsernameOrEmailAddress(String username, String email);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmailAddress(String email);
}
