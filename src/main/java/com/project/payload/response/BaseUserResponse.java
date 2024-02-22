package com.project.payload.response;

import com.project.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class BaseUserResponse {

    private Long id;
    private String userName;
    private String ssn;
    private String name;
    private String surname;
    private String birthDay;
    private String birthPlace;
    private String phoneNumber;
    private String email;
    private Gender gender;

}