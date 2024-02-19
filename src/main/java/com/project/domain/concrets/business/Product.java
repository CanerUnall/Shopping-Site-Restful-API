package com.project.domain.concrets.business;

import com.project.domain.enums.CategoryNames;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private double price;

    private int stockAmount;

    private boolean discount;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private CategoryNames categoryNames;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @ManyToMany(mappedBy = "allProduct")
    private List<PurchasedProductHistory> purchasedProductHistories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductInfo> productInfoList;
}
