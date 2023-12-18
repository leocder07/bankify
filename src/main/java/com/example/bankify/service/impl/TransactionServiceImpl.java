/**
 * Class: TransactionServiceImpl
 * <p>
 * Description: This class serves as...
 * <p>
 * Author: vivekkumar Date Created: 18/12/23 Version: 1.0
 */
package com.example.bankify.service.impl;

import com.example.bankify.api.errors.ErrorCode;
import com.example.bankify.api.exceptions.CustomException;
import com.example.bankify.dto.TransactionDto;
import com.example.bankify.model.Account;
import com.example.bankify.model.Transaction;
import com.example.bankify.model.User;
import com.example.bankify.repository.AccountRepository;
import com.example.bankify.repository.TransactionRepository;
import com.example.bankify.repository.UserRepository;
import com.example.bankify.service.TransactionService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;
  private final AccountRepository accountRepository;
  private final UserRepository userRepository;

  @Override
  @Transactional
  public TransactionDto makeTransfer(TransactionDto transactionDto, String username) {
    Optional<User> userOptional = userRepository.findByUsername(username);
    if (userOptional.isEmpty()) {
      throw new CustomException(ErrorCode.USER_NOT_FOUND);
    }
    User user = userOptional.get();

    Account fromAccount = accountRepository.findByIdAndUser(transactionDto.getFromAccountId(),
        user);
    Account toAccount = accountRepository.findById(transactionDto.getToAccountId())
        .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND));

    if (fromAccount.getBalance().compareTo(transactionDto.getAmount()) < 0) {
      throw new CustomException(ErrorCode.INSUFFICIENT_BALANCE);
    }

    fromAccount.setBalance(fromAccount.getBalance().subtract(transactionDto.getAmount()));
    toAccount.setBalance(toAccount.getBalance().add(transactionDto.getAmount()));

    accountRepository.save(fromAccount);
    accountRepository.save(toAccount);

    Transaction transaction = new Transaction();
    transaction.setFromAccount(fromAccount);
    transaction.setToAccount(toAccount);
    transaction.setAmount(transactionDto.getAmount());
    transaction.setTimestamp(LocalDateTime.now());
    transaction.setDescription(transactionDto.getDescription());
    Transaction savedTransaction = transactionRepository.save(transaction);

    return mapToTransactionDto(savedTransaction, fromAccount.getId(), toAccount.getId());
  }

  @Override
  public List<TransactionDto> getTransactionHistory(String username) {
    Optional<User> userOptional = userRepository.findByUsername(username);
    if (userOptional.isEmpty()) {
      throw new CustomException(ErrorCode.USER_NOT_FOUND);
    }
    User user = userOptional.get();
    List<Account> userAccounts = accountRepository.findByUserId(user.getId());

    return userAccounts.stream()
        .flatMap(account -> transactionRepository.findByFromAccountId(account.getId()).stream())
        .map(transaction -> mapToTransactionDto(transaction, transaction.getFromAccount().getId(),
            transaction.getToAccount().getId())) // Adjust 'toAccountId' if needed
        .toList();
  }

  private TransactionDto mapToTransactionDto(Transaction transaction, Long fromAccountId,
      Long toAccountId) {
    TransactionDto dto = new TransactionDto();
    dto.setTransactionId(transaction.getId());
    dto.setFromAccountId(fromAccountId);
    dto.setToAccountId(toAccountId);
    dto.setAmount(transaction.getAmount());
    dto.setDescription(transaction.getDescription());
    dto.setTimestamp(transaction.getTimestamp().toString());
    return dto;
  }
}
