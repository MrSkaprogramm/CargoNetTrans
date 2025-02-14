package com.androidcargo.spring.models.admin;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import com.androidcargo.spring.models.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Admin")
public class Admin {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int adminId;
  @Column(name = "permission")
  @JdbcTypeCode(SqlTypes.NAMED_ENUM)
  private Permission permission;
}
