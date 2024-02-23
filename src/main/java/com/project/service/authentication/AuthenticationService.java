package com.project.service.authentication;

import com.project.domain.concrets.user.User;
import com.project.exception.BadRequestException;
import com.project.payload.mappers.AuthenticationMapper;
import com.project.payload.mappers.UserMapper;
import com.project.payload.messages.ExceptionMessages;
import com.project.payload.messages.SuccessMessages;
import com.project.payload.request.authentication.LoginRequest;
import com.project.payload.request.business.UpdatePasswordRequest;
import com.project.payload.response.authentication.AuthResponse;
import com.project.payload.response.business.ResponseMessage;
import com.project.payload.response.user.UserResponse;
import com.project.repository.user.UserRepository;
import com.project.security.jwt.JwtUtils;
import com.project.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final AuthenticationMapper authenticationMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    /*public ResponseEntity<AuthResponse> authenticateUser(LoginRequest loginRequest) {
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = "Bearer "+ jwtUtils.generateJwtToken(authentication);

        *//*User user = userRepository.findByUsernameEquals(userName);// bu sekilde username ile kullaniciya ulasabiliriz.
        String role = user.getUserRole().getRoleName();*//*

        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();

        Set<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());


        String name = userDetails.getName();

        AuthResponse authResponse = authenticationMapper.mapToAuthResponse(userName,roles,name,token);

        return ResponseEntity.ok(authResponse);

    }*/
    public ResponseMessage<AuthResponse> authenticateUser(LoginRequest loginRequest) {

        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = "Bearer "+ jwtUtils.generateJwtToken(authentication);

        /*User user = userRepository.findByUsernameEquals(userName);// bu sekilde username ile kullaniciya ulasabiliriz.
        String role = user.getUserRole().getRoleName();*/

        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();

        Set<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());


        String name = userDetails.getName();

        AuthResponse authResponse = authenticationMapper.mapToAuthResponse(userName,roles,name,token);

        return ResponseMessage.<AuthResponse>builder().httpStatus(HttpStatus.OK).object(authResponse).build();
    }

    /*public ResponseEntity<UserResponse> findByUserName(Object userName) {
        User user = userRepository.findByUsernameEquals((String) userName);
        UserResponse userResponse= userMapper.mapUserToUserResponse(user);
        return ResponseEntity.ok(userResponse);
    }*/
    public ResponseMessage<UserResponse> findByUserName(Object userName) {
        User user = userRepository.findByUsernameEquals((String) userName);
        UserResponse userResponse= userMapper.mapUserToUserResponse(user);
        return ResponseMessage.<UserResponse>builder().object(userResponse).httpStatus(HttpStatus.OK).build();
    }


    /*public void updatePassword(UpdatePasswordRequest updatePasswordRequest, HttpServletRequest request) {
        String userName= (String) request.getAttribute("userName");
        User user = userRepository.findByUsernameEquals(userName);

        if (Boolean.TRUE.equals(user.getBuilt_in())){
            throw new BadRequestException(ExceptionMessages.NOT_PERMITTED_THIS_OPERATION);
        }

        if (!passwordEncoder.matches(updatePasswordRequest.getOldPassword(),user.getPassword())){
           throw new BadRequestException(ExceptionMessages.PASSWORD_NOT_MATCHED);
        }

        *//*String encodedPassword = passwordEncoder.encode(updatePasswordRequest.getNewPassword());
        user.setPassword(encodedPassword);*//*
        user.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));

        userRepository.save(user);

    }*/
    public ResponseMessage<String> updatePassword(UpdatePasswordRequest updatePasswordRequest, HttpServletRequest request) {
        String userName= (String) request.getAttribute("userName");
        User user = userRepository.findByUsernameEquals(userName);

        if (Boolean.TRUE.equals(user.getBuilt_in())){
            throw new BadRequestException(ExceptionMessages.NOT_PERMITTED_THIS_OPERATION);
        }

        if (!passwordEncoder.matches(updatePasswordRequest.getOldPassword(),user.getPassword())){
            throw new BadRequestException(ExceptionMessages.PASSWORD_NOT_MATCHED);
        }

        /*String encodedPassword = passwordEncoder.encode(updatePasswordRequest.getNewPassword());
        user.setPassword(encodedPassword);*/
        user.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));

        userRepository.save(user);

        return ResponseMessage.<String>builder().httpStatus(HttpStatus.OK).object(SuccessMessages.PASSWORD_UPDATED_RESPONSE_MESSAGE).build();
    }


}
