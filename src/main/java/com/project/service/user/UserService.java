package com.project.service.user;

import com.project.domain.concrets.user.User;
import com.project.domain.concrets.user.UserRole;
import com.project.domain.enums.RoleType;
import com.project.exception.BadRequestException;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.mappers.UserMapper;
import com.project.payload.messages.ExceptionMessages;
import com.project.payload.messages.SuccessMessages;
import com.project.payload.request.user.UserRequest;
import com.project.payload.request.user.UserRequestWithoutPassword;
import com.project.payload.response.BaseUserResponse;
import com.project.payload.response.business.ResponseMessage;
import com.project.payload.response.user.UserResponse;
import com.project.repository.user.UserRepository;
import com.project.service.helper.MethodHelper;
import com.project.service.helper.PageableHelper;
import com.project.service.validation.UniqueValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UniqueValidator uniqueValidator;
    private final UserMapper userMapper;
    private final UserRoleService userRoleService;
    private final PageableHelper pageableHelper;
    private final MethodHelper methodHelper;

    public ResponseMessage<UserResponse> saveUser(UserRequest userRequest, String userRole) {
        uniqueValidator.checkUniqueCondition(userRequest);

        User user = userMapper.mapUserRequestToUser(userRequest);

        for (RoleType roleType : RoleType.values()) {
            if (roleType.name().equals(userRole)) {
                /*user.getUserRole().setRoleType(roleType);
                user.setUserRole();*/
                user.setUserRole(userRoleService.getUserRole(roleType));
            }
        }

        User savedUser = userRepository.save(user);

        return ResponseMessage.<UserResponse>builder().message(SuccessMessages.USER_SAVED_SUCCESSFULLY)
                .httpStatus(HttpStatus.CREATED).object(userMapper.mapUserToUserResponse(savedUser)).build();
    }


    public ResponseMessage<BaseUserResponse> getUserById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException(ExceptionMessages.USER_NOT_FOUND));

        BaseUserResponse baseUserResponse = null;

        if (user.getUserRole().getRoleType().equals(RoleType.SELLER)){
            baseUserResponse = userMapper.mapUserToSellerResponse(user);
        } else if (user.getUserRole().getRoleType().equals(RoleType.CUSTOMER)) {
            baseUserResponse = userMapper.mapUserToCustomerResponse(user);
        }else {
            baseUserResponse = userMapper.mapUserToUserResponse(user);
        }

        return ResponseMessage.<BaseUserResponse>builder().message(SuccessMessages.USER_FOUNDED_SUCCESSFULLY).httpStatus(HttpStatus.FOUND)
                .object(baseUserResponse).build();
    }

    public Page<UserResponse> getAllUserWithRole(String userRole, int page, int size, String sort, String type) {
        Pageable pageable = pageableHelper.returnPageable(page,size,sort,type);

        return userRepository.findByUserRole(userRole,pageable).map(userMapper::mapUserToUserResponse);

    }

    public ResponseMessage<String> deleteUserById(Long userId, HttpServletRequest request) {

        User user = methodHelper.findUserOrThrowException(userId);

        String userName = (String) request.getAttribute("userName");

        User user1 = userRepository.findByUsernameEquals(userName);

        methodHelper.checkBuild_inUser(user);

        if (! (user1.getUserRole().getRoleType().getNumberOfStairs()>user.getUserRole().getRoleType().getNumberOfStairs())){
            throw  new BadRequestException(ExceptionMessages.NOT_PERMITTED_THIS_OPERATION);
        }

        userRepository.deleteById(userId);
        return ResponseMessage.<String>builder().object(SuccessMessages.USER_DELETED_SUCCESSFULLY).build();
    }

    public ResponseEntity<BaseUserResponse> updateUser(UserRequest userRequest, Long userId) {
        User user = methodHelper.findUserOrThrowException(userId);

        methodHelper.checkBuild_inUser(user);

        uniqueValidator.checkUniqueCondition(userRequest);

        User user1 = userMapper.mapUserRequestToUserForUpdate(userRequest,userId);

        user1.setUserRole(user.getUserRole());

        User savedUser = userRepository.save(user1);

        return new ResponseEntity<>(userMapper.mapUserToUserResponse(savedUser),HttpStatus.OK);

    }

    public ResponseMessage<String> updateUserOwnInfo(UserRequestWithoutPassword userRequestWithoutPassword, HttpServletRequest request) {

        String userName = (String) request.getAttribute("userName");
        User user = userRepository.findByUsernameEquals(userName);

        methodHelper.checkBuild_inUser(user);

        uniqueValidator.checkUniqueCondition(userRequestWithoutPassword);

        User user1= userMapper.mapUserRequestToUserForUpdateOwnInfo(userRequestWithoutPassword);

        userRepository.save(user1);

        return ResponseMessage.<String>builder().object(SuccessMessages.USER_UPDATED_SUCCESSFULLY).build();
    }
}