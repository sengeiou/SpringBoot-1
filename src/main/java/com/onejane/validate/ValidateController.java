package com.onejane.validate;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @description : 跳转渲染模板引擎 默认模板的存放位置为classpath:templates
 */
@Controller
@RequestMapping("validate")
@Log
public class ValidateController {

    /**
     * 登录
     * @param vo 前端返回过来用户账号和密码
     * @return 登录成功的信息or失败信息
     */
    @RequestMapping(path="/do_login",method= RequestMethod.POST)
    @ResponseBody
    public Result do_login(@Valid LoginVo vo){
        log.info(vo.toString());
        String mobile = vo.getMobile();
        String password = vo.getPassword();
        ResultEnum resultEnum = ResultEnum.SUCCESS;
        if (resultEnum.getCode() == 0){
            return Result.success(resultEnum);
        }else {
            return Result.error(resultEnum);
        }
    }
}
