package com.project.payload.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseUserRequestWithPassword extends BaseUserRequest{

    @NotNull(message = "Please enter your password")
    @Size(min = 10,max = 30, message = "Your password must be min 10 chars or max 30 chars")
    private String password;

    private Boolean built_in;

}
