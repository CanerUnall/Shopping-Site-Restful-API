package com.project.contactmessage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ContactMessageRequest {


    @NotNull(message = "Please enter name")
    @Size(min = 3, max = 20, message = "Your name can be at least 3 chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+",message = "Your message must consist of the character")
    private String name;

    @Email(message = "Please enter a valid email")
    @NotNull(message = "Please enter your email")
    @Size(min = 5,max = 20, message = "Your email can be at least 5 chars")
    private String email;


    @NotNull(message = "Please enter your subject")
    @Size(min = 5,max = 50, message = "Your subject can be at least 5 chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+",message = "Your subject must consist of the character")
    private String subject;


    @NotNull(message = "Please enter your message")
    @Size(min = 5,max = 50, message = "Your email can be at least 5 chars")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+",message = "Your subject must consist of the character")
    private String message;

}