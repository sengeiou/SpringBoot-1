package com.onejane.validate;
 
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
 
@Target({TYPE, METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })// 约束注解应用的目标元素类型(TYPE   ,METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER等)
@Retention(RUNTIME)// 约束注解应用的时机
@Documented
@Constraint(validatedBy = { IsMobileValidator.class})// 与约束注解关联的验证器
public @interface IsMobile {
 
    boolean required() default true;//是否校验
 
    String message() default "手机号码错误"; // 约束注解验证时的输出消息
 
    Class<?>[] groups() default { };// 约束注解在验证时所属的组别
 
    Class<? extends Payload>[] payload() default { };// 约束注解的有效负载
}