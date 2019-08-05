package com.onejane.validate;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
 
@Data //此注解是lombox插件,用于生产set和get以及toString等方法
public class LoginVo {
    @NotNull //不能为空
    @IsMobile //自定义注解校验是否满足手机号格式
    private String mobile;
 
    @NotNull //不能为空
    @Length(min = 6) //指定长度
    private String password;
}