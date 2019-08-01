package com.onejane.jpa.entity;

import com.onejane.jpa.vo.RoleUserId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@IdClass(RoleUserId.class)
@Table(name = "AUTH_ROLE_USER")
public class RoleUserEntity  {
    @Id
    private Long roleId;
    @Id
    private Long userId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}