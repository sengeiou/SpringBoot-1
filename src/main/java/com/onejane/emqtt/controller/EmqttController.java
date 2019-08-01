package com.onejane.emqtt.controller;

import com.onejane.emqtt.gateway.MqttGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: codewj
 * @Date: 2019/8/1 11:05
 * @Description: https://github.com/emqx/emqx/releases  emqx-centos7-v3.2.1.zip解压到/usr/local
 *               ./bin/emqx status	查看可执行命令
 *               http://144.34.177.162:18083/#/ admin/public
 *               http://192.168.3.233:10000/say
 *
 *               wget https://www.emqx.io/downloads/broker/v3.2.1/emqx-docker-v3.2.1-amd64.zip
 *               unzip emqx-docker-v3.2.1-amd64.zip
 *               docker load < emqx-docker-v3.2.1-amd64
 *               docker run -di --name emqtt -p 1883:1883 -p 8883:8883 -p 8083:8083 -p 8080:8888 -p 18083:18083 emqx/emqx:v3.2.1-amd64
 *
 *               安装MQTTLens插件，chrome://apps/，测试查看emqtt/mqttlens.png
 */
@RestController
public class EmqttController {
    @Autowired
    MqttGateway mqttGateway;

    @RequestMapping("/say")
    public String say(@RequestParam(name = "topic",required = false,defaultValue = "topic_ht") String topic, @RequestParam(name = "msg",required = false,defaultValue = "来自华通小王的亲切问候") String msg){
        mqttGateway.sendToMqtt(topic,1,msg);
        return "emqx demo";
    }

    @RequestMapping(value = "/emqwebhook")
    public void  emqwebhook(@RequestBody String ps){
        mqttGateway.sendToMqtt("topic_ht",1,ps);
        System.out.println(ps);
    }
}
