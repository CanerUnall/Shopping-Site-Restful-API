package com.project.repository.user;

import com.project.domain.concrets.user.UserRole;
import com.project.domain.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    @Query("SELECT u FROM UserRole u WHERE u.roleType = ?1")
    Optional<UserRole> findByRoleType(RoleType roleType);

}
