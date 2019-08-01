package com.onejane.poi.vo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 32)
    private String name;
    @Column(length = 32)
    private String phone;
    @Column(length = 132)
    private String address;
    @Column
    private Date enrolDate;
    @Column(length = 132)
    private String des;
}