package com.project.domain.concrets.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.domain.concrets.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BonusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bonusHistoryId;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User customer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime transactionDate;

    private double amount;

}
