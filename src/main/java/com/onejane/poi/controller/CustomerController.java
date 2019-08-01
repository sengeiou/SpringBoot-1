package com.onejane.poi.controller;

import com.onejane.poi.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "测试Excel")
@RestController
@RequestMapping("/test/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "导入", notes = "导入")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件", paramType = "query", dataType = "file"),
            @ApiImplicitParam(name = "note", value = "备注", paramType = "query", dataType = "String")
    })
    @PostMapping("/import")
    public boolean addUser(@RequestParam("file") MultipartFile file,String note) {
        boolean a = false;
        System.out.println(note);
        String fileName = file.getOriginalFilename();
        try {
            a = customerService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }
}