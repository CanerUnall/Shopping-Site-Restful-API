package com.project.domain.concrets.business;

import com.project.domain.concrets.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User customer;

    @OneToMany(mappedBy = "shoppingCart",cascade = CascadeType.REMOVE)
    private List<ProductInfo> productList;

    private boolean isPaid;

}