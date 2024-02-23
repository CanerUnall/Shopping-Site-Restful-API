package com.project.payload.request.user;

import com.project.payload.request.BaseUserRequestWithPassword;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserRequest extends BaseUserRequestWithPassword {
}
