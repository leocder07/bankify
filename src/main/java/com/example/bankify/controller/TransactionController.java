/**
 * Class: TransactionController
 * <p>
 * Description: This class serves as...
 * <p>
 * Author: vivekkumar Date Created: 18/12/23 Version: 1.0
 */
package com.example.bankify.controller;

import com.example.bankify.api.errors.ErrorCode;
import com.example.bankify.api.exceptions.CustomException;
import com.example.bankify.dto.TransactionDto;
import com.example.bankify.service.TransactionService;
import com.example.bankify.utils.SecurityUtils;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

  private final TransactionService transactionService;

  @Autowired
  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @PostMapping("/transfer")
  public ResponseEntity<TransactionDto> makeTransfer(@RequestBody TransactionDto transactionDto, @RequestParam String username) {
    TransactionDto result = transactionService.makeTransfer(transactionDto, username);
    return ResponseEntity.ok(result);
  }

  @GetMapping("/")
  public ResponseEntity<List<TransactionDto>> getTransactionHistory(@RequestParam String username) {
    String loggedInUser =  SecurityUtils.getCurrentUsername();
    if(!loggedInUser.equals(username)){
      throw new CustomException(ErrorCode.AUTHORIZATION_FAILED);
    }
    List<TransactionDto> history = transactionService.getTransactionHistory(username);
    return ResponseEntity.ok(history);
  }
}