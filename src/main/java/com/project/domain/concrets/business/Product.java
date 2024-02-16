package com.project.domain.concrets.business;

import com.project.domain.enums.CategoryNames;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private CategoryNames categoryNames;

    @ManyToOne
    @JoinColumn(name = "productList")
    private ShoppingCart shoppingCart;
}
