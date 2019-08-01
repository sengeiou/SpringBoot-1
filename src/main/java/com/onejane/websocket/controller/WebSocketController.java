package com.onejane.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: codewj
 * @Date: 2019/5/31 14:21
 * @Description:
 */
@Controller
@CrossOrigin
@RequestMapping("/websocket")
public class WebSocketController {
    @RequestMapping(value = "/chat")
    public String chat(){
        return "chatroom";
    }
}
