package com.androidcargo.spring.models.car;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "body_type")
    private BodyType bodyType;
    @Column(name = "capacity")
    private double capacity;
    @Column(name = "width")
    private double width;
    @Column(name = "height")
    private double height;
}
