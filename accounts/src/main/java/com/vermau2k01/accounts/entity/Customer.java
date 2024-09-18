package com.vermau2k01.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue
    private Long customerId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    @NaturalId
    private String email;
    @Column(nullable = false)
    private String mobileNumber;
}
