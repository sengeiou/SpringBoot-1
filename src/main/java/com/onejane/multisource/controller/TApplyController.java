package com.onejane.multisource.controller;

import com.onejane.multisource.entity.TApply;
import com.onejane.multisource.service.TApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "apply")
public class TApplyController {
    @Autowired
    private TApplyService tApplyService;

    @GetMapping
    public List<TApply> getByAskedName(){
        return tApplyService.getAll();
    }


}
