package com.onejane.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 项目启动将根据spring.jpa.hibernate.ddl-auto=update自动创建表
 * 引入lombok依赖，并@Data自动生成set get toString等方法，配置idea plugins的lombok插件
 */
@Data
@Entity
@Table(name = "AUTH_USER")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {
    @Id
    private Long id;
    @Column(length = 32)
    private String name;
    @Column(length = 32)
    private String account;
    @Column(length = 64)
    private String pwd;

}