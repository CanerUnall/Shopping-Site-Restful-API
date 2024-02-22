package com.project.domain.concrets.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.domain.concrets.business.BalanceHistory;
import com.project.domain.concrets.business.BonusHistory;
import com.project.domain.concrets.business.PurchasedProductHistory;
import com.project.domain.concrets.business.ShoppingCart;
import com.project.domain.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)

@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userName;

    @Column(unique = true)
    private String ssn;

    private String name;

    private String surname;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDay;

    private String birthPlace;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(unique = true)
    private String PhoneNumber;

    @Column(unique = true)
    private String email;

    private Boolean built_in;//eger dokunulmasini istemedigim bir user olursa bunu true yap.

    private int customerNumber;

    private boolean isActive;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    private Address address;

    @Embedded
    private BankCard bankCard;

    @JsonIgnore
    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
    private List<ShoppingCart> shoppingCarts;

    @JsonIgnore
    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
    private List<BalanceHistory> balanceHistories;

    @JsonIgnore
    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
    private List<BonusHistory> bonusHistories;

    @JsonIgnore
    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
    private List<PurchasedProductHistory> purchasedProductHistories;

    @OneToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserRole userRole;
}