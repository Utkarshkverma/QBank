package com.vermau2k01.accounts.repository;

import com.vermau2k01.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
