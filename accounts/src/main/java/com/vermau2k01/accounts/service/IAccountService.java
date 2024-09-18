package com.vermau2k01.accounts.service;


import com.vermau2k01.accounts.dto.CustomerDto;
import com.vermau2k01.accounts.entity.Account;

public interface IAccountService {

    /**
     * @param customerDto - CustomerDto object
     **/
    void createAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Accounts Details based on a given mobileNumber
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    boolean deleteAccount(String mobileNumber);


}
