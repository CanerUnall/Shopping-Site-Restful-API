package com.project.payload.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.domain.concrets.business.BalanceHistory;
import com.project.domain.concrets.business.BonusHistory;
import com.project.domain.concrets.business.PurchasedProductHistory;
import com.project.domain.concrets.business.ShoppingCart;
import com.project.payload.response.BaseUserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse extends BaseUserResponse {

    private int customerNumber;
    private boolean isActive;
    private List<ShoppingCart> shoppingCarts;
    private List<BalanceHistory> balanceHistories;
    private List<BonusHistory> bonusHistories;
    private List<PurchasedProductHistory> purchasedProductHistories;
}
