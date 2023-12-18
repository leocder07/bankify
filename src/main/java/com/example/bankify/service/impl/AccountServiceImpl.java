/**
 * Class: AccountServiceImpl
 * <p>
 * Description: This class serves as...
 * <p>
 * Author: vivekkumar Date Created: 18/12/23 Version: 1.0
 */
package com.example.bankify.service.impl;

import com.example.bankify.dto.AccountDto;
import com.example.bankify.model.Account;
import com.example.bankify.model.User;
import com.example.bankify.repository.AccountRepository;
import com.example.bankify.repository.UserRepository;
import com.example.bankify.service.AccountService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;
  private final UserRepository userRepository;

  @Override
  public AccountDto createAccount(AccountDto accountDto, String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

    Account account = new Account();
    account.setAccountNumber(accountDto.getAccountNumber());
    account.setBalance(Optional.ofNullable(accountDto.getInitialBalance()).orElse(BigDecimal.ZERO));
    account.setAccountType(accountDto.getAccountType());
    account.setUser(user);
    Account savedAccount = accountRepository.save(account);

    return mapToAccountDto(savedAccount);
  }

  @Override
  public AccountDto getAccountDetails(Long accountId, String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
    Account account = accountRepository.findByIdAndUser(accountId, user);

    return mapToAccountDto(account);
  }

  private AccountDto mapToAccountDto(Account account) {
    AccountDto accountDto = new AccountDto();
    accountDto.setAccountId(account.getId());
    accountDto.setUserId(account.getUser().getId());
    accountDto.setAccountNumber(account.getAccountNumber());
    accountDto.setInitialBalance(account.getBalance());
    accountDto.setAccountType(account.getAccountType());
    return accountDto;
  }
}
