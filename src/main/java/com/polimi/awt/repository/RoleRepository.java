package com.polimi.awt.repository;

import com.polimi.awt.model.Role;
import com.polimi.awt.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    //Role findByName(RoleName roleName);

    Role findByName(RoleName roleName);
}
