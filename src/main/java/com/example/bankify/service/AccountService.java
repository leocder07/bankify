/**
 * Interface: AccountService
 * <p>
 * Description: Defines the contract for...
 * <p>
 * Author: vivekkumar Date Created: 18/12/23 Version: 1.0
 */
package com.example.bankify.service;

import com.example.bankify.dto.AccountDto;
import com.example.bankify.model.Account;

public interface AccountService {
  AccountDto createAccount(AccountDto accountDto,String username);
  AccountDto getAccountDetails(Long accountId, String username);

}
