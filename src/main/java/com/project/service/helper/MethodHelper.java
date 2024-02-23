package com.project.service.helper;

import com.project.domain.concrets.user.User;
import com.project.exception.BadRequestException;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.messages.ExceptionMessages;
import com.project.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MethodHelper {

    private final UserRepository userRepository;

    public User findUserOrThrowException(Long userId){
        return userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException(ExceptionMessages.USER_NOT_FOUND));
    }

    public void checkBuild_inUser(User user){
        if (Boolean.TRUE.equals(user.getBuilt_in())){
            throw  new BadRequestException(ExceptionMessages.NOT_PERMITTED_THIS_OPERATION);
        }
    }

}
