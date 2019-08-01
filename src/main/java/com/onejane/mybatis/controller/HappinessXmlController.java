package com.onejane.mybatis.controller;

import com.onejane.mybatis.entity.Happiness;
import com.onejane.mybatis.service.HappinessXmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/xmldemo")
public class HappinessXmlController {
    @Autowired
    private HappinessXmlService happinessXmlService;

    @RequestMapping("/query")
    public Happiness testQuery(){
        return happinessXmlService.selectService("西安");
    }

    @RequestMapping("/insert")
    public Happiness testInsert(){
        happinessXmlService.insertService();
        return happinessXmlService.selectService("西安");
    }
}