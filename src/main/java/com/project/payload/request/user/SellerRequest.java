package com.project.payload.request.user;

import com.project.payload.request.BaseUserRequestWithPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class SellerRequest extends BaseUserRequestWithPassword {

    private String companyName;

    @NotNull(message = "Please enter your seller number")
    private int sellerNumber;
}
