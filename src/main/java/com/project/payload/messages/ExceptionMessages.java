package com.project.payload.messages;

public class ExceptionMessages {
    private ExceptionMessages() {
    }

    public static final String NOT_PERMITTED_THIS_OPERATION="You don't have ant permission to do this operation";

    public static final String PASSWORD_NOT_MATCHED = "Your old password is wrong";

    public static final String ALREADY_REGISTER_USERNAME = "User with username %s is already registered";
    public static final String ALREADY_REGISTER_SSN = "User with ssn %s is already registered";
    public static final String ALREADY_REGISTER_PHONE_NUMBER = "User with phone number %s is already registered";
    public static final String ALREADY_REGISTER_EMAIL = "User with email %s is already registered";

    public static final String ROLE_NOT_FOUND = "Role not found";
    public static final String USER_NOT_FOUND = "User not found";


}
