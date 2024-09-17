package com.androidcargo.spring.models.admin;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import com.androidcargo.spring.models.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Admin")
public class Admin extends User {
  @Column(name = "permission")
  @JdbcTypeCode(SqlTypes.NAMED_ENUM)
  private Permission permission;
}
