package com.project.payload.mappers;

import com.project.exception.ResourceNotFoundException;
import com.project.payload.response.authentication.AuthResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
@Component
public class AuthenticationMapper {


    public AuthResponse mapToAuthResponse(String userName, Set<String> roles, String name, String token) {

        String role= (String)roles.stream().findFirst().orElseThrow(()-> new ResourceNotFoundException("Role Not Found"));

        return AuthResponse.builder().userName(userName).name(name).token(token.substring(7)).role(role).build();

    }
}
