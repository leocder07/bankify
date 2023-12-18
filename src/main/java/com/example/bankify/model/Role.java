/**
 * Class: Role
 * <p>
 * Description: This class serves as...
 * <p>
 * Author: vivekkumar Date Created: 18/12/23 Version: 1.0
 */
package com.example.bankify.model;

import com.example.bankify.enums.ERole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Table(name = "roles")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  ERole name;

}

