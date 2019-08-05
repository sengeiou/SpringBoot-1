package com.onejane.validate;

import org.thymeleaf.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
 
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {
 
    private boolean required = false;
 
    //初始化方法
    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }
 
    //校验方法
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required == false) {
            return ValidatorUtil.isMobile(value);
        }else {
            if (StringUtils.isEmpty(value)) {
                return true;
            }
            return ValidatorUtil.isMobile(value);
        }
    }
}