package com.onejane.validate;

import org.thymeleaf.util.StringUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
/**
 * 手机号格式校验
 */
public class ValidatorUtil {
 
    //手机号格式表达式
    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");
 
    /**
     * 校验src是否为指定的手机号格式
     * @param src 需要校验的手机号
     * @return 是指定的手机号返回true,否则false
     */
    public static boolean isMobile(String src){
        if (StringUtils.isEmpty(src)){
            return false;
        }
        Matcher m = mobile_pattern.matcher(src);
        return m.matches();
    }
}
 