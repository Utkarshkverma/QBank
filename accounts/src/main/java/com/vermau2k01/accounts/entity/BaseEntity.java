package com.vermau2k01.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;


@MappedSuperclass
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Column(updatable = false)
    private LocalDate createdAt;
    @Column(updatable = false)
    private String createdBy;
    @Column(insertable = false)
    private LocalDate updatedAt;
    @Column(insertable = false)
    private String updatedBy;
}
