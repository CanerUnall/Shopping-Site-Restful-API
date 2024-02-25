package com.project.repository.user;

import com.project.domain.concrets.user.User;
import com.project.domain.enums.RoleType;
import com.project.payload.response.user.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserNameEquals(String username);


    boolean existsByUserName(String userName);

    boolean existsBySsn(String ssn);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.userRole.roleName = ?1")
    Page<User> findByUserRole(String userRole, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.userRole.roleType = ?1")
    List<User> findAll(RoleType roleType);


}
