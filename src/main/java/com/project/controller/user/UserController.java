package com.project.controller.user;

import com.project.payload.request.user.UserRequest;
import com.project.payload.request.user.UserRequestWithoutPassword;
import com.project.payload.response.BaseUserResponse;
import com.project.payload.response.business.ResponseMessage;
import com.project.payload.response.user.UserResponse;
import com.project.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/save/{userRole}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ResponseMessage<UserResponse>> saveUser(@RequestBody @Valid UserRequest userRequest,
                                                                  @PathVariable String userRole) {
        return new ResponseEntity<>(userService.saveUser(userRequest, userRole), HttpStatus.OK);
    }


    @GetMapping("/getUserById/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER')")
    public ResponseMessage<BaseUserResponse> getUserById(@PathVariable Long userId) {

        return userService.getUserById(userId);
    }

    @GetMapping("/getAllUser/{userRole}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Page<UserResponse>> getAllUserWithRole(@PathVariable String userRole,
                                                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                                                 @RequestParam(value = "size", defaultValue = "20") int size,
                                                                 @RequestParam(value = "sort", defaultValue = "name") String sort,
                                                                 @RequestParam(value = "type", defaultValue = "desc") String type) {

        return new ResponseEntity<>(userService.getAllUserWithRole(userRole, page, size, sort, type), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER')")
    public ResponseMessage<String> deleteUserById(@PathVariable Long userId, HttpServletRequest request) {
        return userService.deleteUserById(userId, request);
    }

    @PutMapping("/update/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<BaseUserResponse> updateUserWithId(@RequestBody @Valid UserRequest userRequest,
                                                             @PathVariable Long userId){
        return userService.updateUser(userRequest,userId);
    }

    @PatchMapping("/updateUser")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','ASSISTANT_MANAGER','CUSTOMER','SELLER')")
    public ResponseMessage<String> updateUser(@RequestBody @Valid UserRequestWithoutPassword userRequestWithoutPassword,
                                               HttpServletRequest request){
        return userService.updateUserOwnInfo(userRequestWithoutPassword,request);

    }

}