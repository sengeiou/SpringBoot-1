package com.onejane.ali.oss.controller;

import com.onejane.ali.oss.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ClassName DownLoadController
 * @Description TODO
 * @Author xumin zhao
 * @Date 2019/7/4 16:08
 * @Version 1.0
 **/
@RestController
@RequestMapping("/download")
public class DownLoadController {

    @Autowired
    private TransferService transferService;

    /**
     * 根据id获取word文件
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/word")
    public String downLoadWord(@RequestParam(value = "id", required = false) String id) {
        String url = transferService.downLoadWord(id);
        return url;
    }

}
