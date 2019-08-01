package com.onejane.mybatis.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Happiness")
public class Happiness {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(length = 32)
    private String city;
    @Column(length = 32)
    private Integer num;
}