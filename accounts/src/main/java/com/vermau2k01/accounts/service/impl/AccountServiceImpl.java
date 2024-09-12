package com.vermau2k01.accounts.service.impl;

import com.vermau2k01.accounts.constant.AppConstants;
import com.vermau2k01.accounts.dto.AccountDto;
import com.vermau2k01.accounts.dto.CustomerDto;
import com.vermau2k01.accounts.entity.Account;
import com.vermau2k01.accounts.entity.Customer;
import com.vermau2k01.accounts.exception.CustomerAlreadyExistsException;
import com.vermau2k01.accounts.exception.ResourceNotFoundException;
import com.vermau2k01.accounts.mapper.AccountMapper;
import com.vermau2k01.accounts.mapper.CustomerMapper;
import com.vermau2k01.accounts.repository.AccountRepository;
import com.vermau2k01.accounts.repository.CustomerRepository;
import com.vermau2k01.accounts.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;



    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper
                .mapToCustomer(customerDto, new Customer());

        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already" +
                    " registered with given mobileNumber "
                    +customerDto.getMobileNumber());
        }

        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));

    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AppConstants.SAVINGS);
        newAccount.setBranchAddress(AppConstants.ADDRESS);
        return newAccount;
    }



    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Account account = accountRepository
                .findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));



        CustomerDto customerDto = new CustomerDto();
        customerDto.setMobileNumber(mobileNumber);
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setAccountDto(AccountMapper.mapToAccountsDto(account,new AccountDto()));

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountsDto = customerDto.getAccountDto();
        if(accountsDto !=null ){
            Account accounts = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }




}
