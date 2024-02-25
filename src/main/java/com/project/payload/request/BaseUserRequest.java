package com.project.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@SuperBuilder
public abstract class BaseUserRequest {

    @NotNull(message = "Please enter your userName")
    @Size(max = 30, message = "Your userName has max 30 characters")
    private String userName;

    @NotNull
    @Pattern(regexp = "^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$",
            message = "Please enter valid SSN number")
    private String ssn;

    @NotNull(message = "Please enter your name")
    @Size(max = 30, message = "Your name has max 30 characters")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your name must consist of the characters .")
    private String name;

    @NotNull(message = "Please enter your surName")
    @Size(max = 30, message = "Your name has max 30 characters")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your name must consist of the characters .")
    private String surname;

    @NotNull(message = "Please enter your birthday")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Past(message = "Your birthday cant be in the future")
    private LocalDate birthDay;

    @NotNull(message = "Please enter your birthPlace")
    @Size(max = 30, message = "Your birthPlace has max 30 characters")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Your birthPlace must consist of the characters .")
    private String birthPlace;

    @NotNull(message = "Please enter your phoneNumber")
    @Size(min = 12, max = 12,message = "Your phone number should be 12 characters long")
    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
            message = "Please enter valid phone number")
    private String phoneNumber;

    @Email(message = "Please enter your email")
    @NotNull(message = "Please enter your email")
    @Size(max = 40, message = "Your birthPlace has max 30 characters")
    private String email;

    @NotNull(message = "Please enter your gender")
    private Gender gender;




}
