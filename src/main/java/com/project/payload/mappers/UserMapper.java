package com.project.payload.mappers;

import com.project.domain.concrets.user.User;
import com.project.payload.request.user.UserRequest;
import com.project.payload.request.user.UserRequestWithoutPassword;
import com.project.payload.response.BaseUserResponse;
import com.project.payload.response.user.CustomerResponse;
import com.project.payload.response.user.SellerResponse;
import com.project.payload.response.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserResponse mapUserToUserResponse(User user) {
        return UserResponse.builder().id(user.getId()).userName(user.getUserName()).name(user.getName()).surname(user.getSurname())
                .ssn(user.getSsn()).birthDay(user.getBirthDay().toString()).birthPlace(user.getBirthPlace()).phoneNumber(user.getPhoneNumber())
                .email(user.getEmail()).gender(user.getGender()).build();
    }

    public User mapUserRequestToUser(UserRequest userRequest) {

        return User.builder().userName(userRequest.getUserName()).name(userRequest.getName()).surname(userRequest.getSurname())
                .password(passwordEncoder.encode(userRequest.getPassword()))//passwordu hashladim
                .ssn(userRequest.getSsn()).birthDay(userRequest.getBirthDay()).birthPlace(userRequest.getBirthPlace()).phoneNumber(userRequest.getPhoneNumber()).gender(userRequest.getGender())
                .email(userRequest.getEmail()).built_in(userRequest.getBuilt_in()).build();
    }

    public SellerResponse mapUserToSellerResponse(User user) {
        return SellerResponse.builder().id(user.getId()).userName(user.getUserName()).name(user.getName()).surname(user.getSurname()).birthDay(user.getBirthDay().toString())
                .birthPlace(user.getBirthPlace()).ssn(user.getSsn()).phoneNumber(user.getPhoneNumber()).gender(user.getGender()).email(user.getEmail()).companyName(user.getCompanyName())
                .sellerNumber(user.getSellerNumber()).build();
    }

    public CustomerResponse mapUserToCustomerResponse(User user) {

        return CustomerResponse.builder().id(user.getId()).userName(user.getUserName()).name(user.getName()).surname(user.getSurname()).birthDay(user.getBirthDay().toString())
                .birthPlace(user.getBirthPlace()).ssn(user.getSsn()).phoneNumber(user.getPhoneNumber()).gender(user.getGender()).email(user.getEmail())
                .customerNumber(user.getCustomerNumber()).isActive(user.isActive()).shoppingCarts(user.getShoppingCarts()).balanceHistories(user.getBalanceHistories())
                .bonusHistories(user.getBonusHistories()).purchasedProductHistories(user.getPurchasedProductHistories()).build();
    }

    public User mapUserRequestToUserForUpdate(UserRequest userRequest, Long userId) {
        return User.builder().id(userId).userName(userRequest.getUserName()).name(userRequest.getName()).surname(userRequest.getSurname())
                .password(passwordEncoder.encode(userRequest.getPassword()))//passwordu hashladim
                .ssn(userRequest.getSsn()).birthDay(userRequest.getBirthDay()).birthPlace(userRequest.getBirthPlace()).phoneNumber(userRequest.getPhoneNumber()).gender(userRequest.getGender())
                .email(userRequest.getEmail()).built_in(userRequest.getBuilt_in()).build();
    }
    public User mapUserRequestToUserForUpdateOwnInfo(UserRequestWithoutPassword userRequest) {

        return User.builder().userName(userRequest.getUserName()).name(userRequest.getName()).surname(userRequest.getSurname())
                .ssn(userRequest.getSsn()).birthDay(userRequest.getBirthDay()).birthPlace(userRequest.getBirthPlace()).phoneNumber(userRequest.getPhoneNumber()).gender(userRequest.getGender())
                .email(userRequest.getEmail()).build();
    }
}
