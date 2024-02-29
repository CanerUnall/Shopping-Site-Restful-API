package com.project.controller.authentication;

import com.project.payload.request.authentication.LoginRequest;
import com.project.payload.request.business.UpdatePasswordRequest;
import com.project.payload.response.authentication.AuthResponse;
import com.project.payload.response.business.ResponseMessage;
import com.project.payload.response.user.UserResponse;
import com.project.service.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;


    /*@PostMapping("/login") //http://localhost:8080/auth/login + POST
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        return authenticationService.authenticateUser(loginRequest);
    }*/

    @PostMapping("/login") //http://localhost:8080/auth/login + POST
    public ResponseMessage<AuthResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        return authenticationService.authenticateUser(loginRequest);
    }

    /*@PreAuthorize("hasAnyAuthority('MANAGER','ASSISTANT_MANAGER','ADMIN','CUSTOMER')")
    @GetMapping("/user")
    public ResponseEntity<UserResponse> findByUserName(HttpServletRequest request) {
        return authenticationService.findByUserName(request.getAttribute("userName"));
    }*/
    @PreAuthorize("hasAnyAuthority('MANAGER','ASSISTANT_MANAGER','ADMIN','CUSTOMER')")
    @GetMapping("/user")
    public ResponseMessage<UserResponse> findByUserName(HttpServletRequest request) {
        return authenticationService.findByUserName(request.getAttribute("userName"));
    }

    //putmapping bu objeyi komple update eder
    //patchmapping objenin belirli kisimlarini update ediyor
    /*@PatchMapping("/updatePassword")
    @PreAuthorize("hasAnyAuthority('MANAGER','ASSISTANT_MANAGER','ADMIN','CUSTOMER')")
    public ResponseEntity<String> updatePassword(@Valid @RequestBody UpdatePasswordRequest updatePasswordRequest,
                                                 HttpServletRequest request) {
        authenticationService.updatePassword(updatePasswordRequest, request);
        return ResponseEntity.ok(SuccessMessages.PASSWORD_UPDATED_RESPONSE_MESSAGE);
    }*/
    @PatchMapping("/updatePassword")
    @PreAuthorize("hasAnyAuthority('MANAGER','ASSISTANT_MANAGER','ADMIN','CUSTOMER','SELLER')")
    public ResponseMessage<String> updatePassword(@Valid @RequestBody UpdatePasswordRequest updatePasswordRequest,
                                                 HttpServletRequest request) {

        return authenticationService.updatePassword(updatePasswordRequest, request);
    }


}
