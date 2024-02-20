package com.project.repository.user;

import com.project.domain.concrets.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameEquals(String username);
}
