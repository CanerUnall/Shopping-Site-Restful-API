package com.project.service.user;

import com.project.domain.concrets.user.UserRole;
import com.project.domain.enums.RoleType;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.messages.ExceptionMessages;
import com.project.repository.user.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRole getUserRole(RoleType roleType){
        return userRoleRepository.findByRoleType(roleType).orElseThrow(()->
                new ResourceNotFoundException(ExceptionMessages.ROLE_NOT_FOUND));

    }

    public List<UserRole> getAllUserRole() {

        return userRoleRepository.findAll();
    }


    public void saveUserRole(UserRole role) {
        userRoleRepository.save(role);
    }
}
