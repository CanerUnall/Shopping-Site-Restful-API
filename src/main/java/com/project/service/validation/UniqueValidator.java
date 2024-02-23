package com.project.service.validation;

import com.project.exception.ConflictException;
import com.project.payload.messages.ExceptionMessages;
import com.project.payload.request.BaseUserRequest;
import com.project.payload.request.user.UserRequest;
import com.project.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueValidator {

    private final UserRepository userRepository;

    public void checkUniqueCondition(BaseUserRequest userRequest) {

        if (userRepository.existsByUserName(userRequest.getUserName())){
            throw  new ConflictException(String.format(ExceptionMessages.ALREADY_REGISTER_USERNAME,userRequest.getUserName()));
        }
        if (userRepository.existsBySsn(userRequest.getSsn())){
            throw  new ConflictException(String.format(ExceptionMessages.ALREADY_REGISTER_SSN,userRequest.getSsn()));
        }
        if (userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())){
            throw  new ConflictException(String.format(ExceptionMessages.ALREADY_REGISTER_PHONE_NUMBER,userRequest.getPhoneNumber()));
        }
        if (userRepository.existsByEmail(userRequest.getEmail())){
            throw  new ConflictException(String.format(ExceptionMessages.ALREADY_REGISTER_EMAIL,userRequest.getEmail()));
        }

    }
}
