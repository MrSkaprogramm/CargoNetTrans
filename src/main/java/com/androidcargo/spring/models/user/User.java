package com.androidcargo.spring.models.user;

import com.androidcargo.spring.models.admin.Admin;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "User")
public class User {
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
  @OneToOne(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "admin_id", unique = true, nullable = false)
  private Admin admin;
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "client_id", unique = true, nullable = false)
  private Client client;
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "driver_id", unique = true, nullable = false)
  private Driver driver;
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "mover_id", unique = true, nullable = false)
  private Mover mover;
}
