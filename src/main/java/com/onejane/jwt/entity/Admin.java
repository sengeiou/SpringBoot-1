package com.onejane.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name="tb_admin")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {

	@Id
	private String id;//ID
	@Column(length = 32)
	private String loginname;//登陆名称
	@Column(length = 256)
	private String password;//密码
	@Column(length = 1)
	private String state;//状态
}