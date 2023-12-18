/**
 * Interface: TransactionService
 * <p>
 * Description: Defines the contract for...
 * <p>
 * Author: vivekkumar Date Created: 18/12/23 Version: 1.0
 */
package com.example.bankify.service;

import com.example.bankify.dto.TransactionDto;
import com.example.bankify.model.Transaction;
import java.util.List;

public interface TransactionService {
  TransactionDto makeTransfer(TransactionDto transactionDto, String username);
  public List<TransactionDto> getTransactionHistory(String username);
}
