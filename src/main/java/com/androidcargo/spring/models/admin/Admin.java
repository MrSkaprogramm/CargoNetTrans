package com.androidcargo.spring.models.admin;

import com.androidcargo.spring.models.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admin")
public class Admin extends User {
  @Column(name = "permission")
  @JdbcTypeCode(SqlTypes.NAMED_ENUM)
  private Permission permission;
}
