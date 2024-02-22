package com.project.payload.mappers;

import com.project.domain.concrets.user.User;
import com.project.payload.response.user.UserResponse;

public class UserMapper {
    public UserResponse mapUserToUserResponse(User user) {
        return UserResponse.builder().id(user.getId()).userName(user.getUserName()).name(user.getName()).surname(user.getSurname())
                .ssn(user.getSsn()).birthDay(user.getBirthDay().toString()).birthPlace(user.getBirthPlace()).phoneNumber(user.getPhoneNumber())
                .email(user.getEmail()).gender(user.getGender()).build();
    }
}
