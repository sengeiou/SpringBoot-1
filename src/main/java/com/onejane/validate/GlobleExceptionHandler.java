package com.onejane.validate;
import lombok.extern.java.Log;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice//所谓加强Controller就是@ControllerAdvice注解，有这个注解的类中的方法的某些注解会应用到所有的Controller里，其中就包括@ExceptionHandler注解。
@ResponseBody//REST风格接口
@Log//lombox插件中的日志注解
public class GlobleExceptionHandler {

    @ExceptionHandler(value = Exception.class)//异常处理(@ControllerAdvice注解注释的controller层和此注解注释的方法,会对所有controller层抛出的异常进行统一处理)
    public Result<String> ExceptionHandler(HttpServletRequest request, Exception e){
        if (e instanceof BindException){
            BindException ex = (BindException)e;
            List<ObjectError> allErrors = ex.getAllErrors();//捕获的所有错误对象
            ObjectError error = allErrors.get(0);
            String defaultMessage = error.getDefaultMessage();//异常内容
            log.info(defaultMessage);//打印日志
            return Result.error(ResultEnum.BIND_ERROR);
        }else {
            return Result.error(ResultEnum.SERVER_ERROR);
        }
    }
}