/**
 * Class: AccountDto
 * <p>
 * Description: This class serves as...
 * <p>
 * Author: vivekkumar Date Created: 18/12/23 Version: 1.0
 */
package com.example.bankify.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AccountDto {
  Long userId;
  Long accountId;
  String accountNumber;
  BigDecimal initialBalance;
  String accountType;
}