package com.project.payload.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.payload.response.BaseUserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellerResponse extends BaseUserResponse {

    private String companyName;
    private int sellerNumber;

}
