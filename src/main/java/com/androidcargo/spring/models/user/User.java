package com.androidcargo.spring.models.user;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int userId;
  @Column(name = "login")
  private String login;
  @Column(name = "password")
  private String password;
  @Column(name = "name")
  private String name;
  @Column(name = "surname")
  private String surname;
  @Column(name = "patronymic")
  private String patronymic;
  @Column(name = "userStatus")
  @JdbcTypeCode(SqlTypes.NAMED_ENUM)
  private UserStatus userStatus;
  @Column(name = "phoneNumber")
  private String phoneNumber;
  @Column(name = "email")
  private String email;
  @Column(name = "acceptRules")
  private boolean acceptRules;
}
