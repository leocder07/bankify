/**
 * Class: Account
 * <p>
 * Description: This class serves as...
 * <p>
 * Author: vivekkumar Date Created: 18/12/23 Version: 1.0
 */
package com.example.bankify.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Table(name = "accounts")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(name = "account_number", unique = true)
  String accountNumber;
  BigDecimal balance;
  String accountType; // e.g., SAVINGS, CHECKING
  String status; // e.g., ACTIVE, CLOSED

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  User user;

  @OneToMany(mappedBy = "fromAccount", cascade = CascadeType.ALL, orphanRemoval = true)
  Set<Transaction> outgoingTransactions = new HashSet<>();

  @OneToMany(mappedBy = "toAccount", cascade = CascadeType.ALL, orphanRemoval = true)
  Set<Transaction> incomingTransactions = new HashSet<>();

}
