package com.example.bankify.controller;
import com.example.bankify.dto.AccountDto;
import com.example.bankify.service.AccountService;
import com.example.bankify.utils.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class: AccountController
 * <p>
 * Description: This class serves as...
 * <p>
 * Author: vivekkumar Date Created: 18/12/23 Version: 1.0
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping
  public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
    String username = SecurityUtils.getCurrentUsername();
    AccountDto createdAccount = accountService.createAccount(accountDto, username);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
  }

  @GetMapping("/{accountId}")
  public ResponseEntity<AccountDto> getAccountDetails(@PathVariable Long accountId) {
    String username = SecurityUtils.getCurrentUsername();
    AccountDto accountDetails = accountService.getAccountDetails(accountId, username);
    return ResponseEntity.ok(accountDetails);
  }
}