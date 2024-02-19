package com.project.domain.concrets.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.domain.concrets.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PurchasedProductHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productHistoryId;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User customer;

    @ManyToMany
    @JoinTable(
            name = "Purchased_Product", // Ara tablonun adı
            joinColumns = @JoinColumn(name = "product_history_id"), // Ara tablodaki satın alım geçmişi ilişkilendirme sütunu
            inverseJoinColumns = @JoinColumn(name = "product_id") // Ara tablodaki ürün ilişkilendirme sütunu
    )
    private List<Product> allProduct;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime transactionDate;

}