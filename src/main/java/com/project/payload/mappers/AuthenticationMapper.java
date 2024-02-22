package com.project.payload.mappers;

import com.project.exception.ResourceNotFoundException;
import com.project.payload.response.authentication.AuthResponse;

import java.util.Optional;
import java.util.Set;

public class AuthenticationMapper {


    public AuthResponse mapToAuthResponse(String userName, Set<String> roles, String name, String token) {

        String role= (String)roles.stream().findFirst().orElseThrow(()-> new ResourceNotFoundException("Role Not Found"));

        return AuthResponse.builder().userName(userName).name(name).token(token.substring(7)).role(role).build();

    }
}
