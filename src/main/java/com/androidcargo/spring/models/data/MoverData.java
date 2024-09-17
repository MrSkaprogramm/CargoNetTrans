package com.androidcargo.spring.models.data;

import java.sql.Blob;
import java.util.Date;

import com.androidcargo.spring.models.user.Mover;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Entity
@Table(name = "MoverData")
public class MoverData {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Lob
  private Blob image;
  private Date date = new Date();
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "mover_id", nullable = false)
  private Mover mover;
}
