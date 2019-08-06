package com.onejane.emqtt.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class LoginRequest extends NettyMessage {
    /**
     * 登陆的账号
     */
    private String userName;
    /**
     * 登陆的密码
     */
    private String password;
    /**
     * 设备的MAC地址
     */
    private String macAddress;


}
