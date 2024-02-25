package com.project.domain.enums;

import lombok.Getter;

@Getter
public enum RoleType {
//    GUEST_CUSTOMER,
    CUSTOMER(0),
    SELLER(1),
    ASSISTANT_MANAGER(2),
    MANAGER(3),
    ADMIN(4);

    final int numberOfStairs;

    RoleType(int numberOfStairs) {
        this.numberOfStairs = numberOfStairs;
    }

}
