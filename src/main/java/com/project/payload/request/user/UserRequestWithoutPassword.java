package com.project.payload.request.user;

import com.project.payload.request.BaseUserRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class UserRequestWithoutPassword extends BaseUserRequest {
}
