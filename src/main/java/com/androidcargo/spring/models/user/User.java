package com.androidcargo.spring.models.user;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
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
  @JdbcTypeCode(SqlTypes.NAMED_ENUM)
  @Column(name = "user_status", columnDefinition = "userstatus")
  private UserStatus userStatus;
  @Column(name = "phone_number")
  private String phoneNumber;
  @Column(name = "email")
  private String email;
  /*@Column(name = "acceptRules")
  private boolean acceptRules;*/
}
