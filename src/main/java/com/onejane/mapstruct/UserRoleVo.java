package com.onejane.mapstruct;

import lombok.Data;

@Data
public class UserRoleVo {
    private Long id;
    private String userName;
    private String roleName;
    private String account;
    private String pwd;
}
