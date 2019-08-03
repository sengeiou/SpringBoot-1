package com.onejane.multisource.controller;

import com.onejane.multisource.service.WebDicService;
import com.onejane.multisource.entity.WebDic;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "webdic")
public class WebDicController {

    @Autowired
    private WebDicService webDicService;

    // http://localhost:9501/user
    @ApiOperation(value = "获取所有字典")
    @RequestMapping
    public List<WebDic> getAll() {
        return webDicService.getAll();
    }

}
