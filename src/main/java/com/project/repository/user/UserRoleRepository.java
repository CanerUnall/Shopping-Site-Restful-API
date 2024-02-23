package com.project.repository.user;

import com.project.domain.concrets.user.UserRole;
import com.project.domain.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    Optional<UserRole> findByRoleType(RoleType roleType);

}
