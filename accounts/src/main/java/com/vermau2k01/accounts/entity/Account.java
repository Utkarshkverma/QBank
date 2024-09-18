package com.vermau2k01.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account extends BaseEntity {

    @Id
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
    private Long customerId;
}
