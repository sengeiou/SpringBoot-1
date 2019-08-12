package com.onejane.emqtt.controller;

import com.onejane.emqtt.entity.LoginRequest;
import com.onejane.emqtt.entity.NettyMessage;
import com.onejane.emqtt.handler.MqttProducer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
 *               mvn install:install-file -Dfile=netty-all-4.1.6.Final.jar -DgroupId=io.netty -DartifactId=netty-all -Dversion=4.1.6.Final -Dpackaging=jar
 *               安装MQTTLens插件，chrome://apps/，测试查看emqtt/mqttlens.png
 */
@RestController
@Slf4j
public class EmqttController {

    @Autowired
    MqttProducer mqttProducer;

    public static MqttClient client;

    @RequestMapping("/say")
    public String say() throws Exception {
        MqttMessage messageReport = new MqttMessage();
        messageReport.setQos(2);
        messageReport.setRetained(false);
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setVersion((short)1);
        loginRequest.setSn("100120201100001B");
        loginRequest.setTimestamp((int) (System.currentTimeMillis()/1000L));
        loginRequest.setCommandType((short) 1);
        loginRequest.setUserName("100120201100001B");
        loginRequest.setPassword("2DMxt799");
        loginRequest.setMacAddress("9C-69-B4-20-00-15");
        NettyMessage nettyMessage=new NettyMessage();
        BeanUtils.copyProperties(loginRequest,nettyMessage);
//        messageReport.setPayload(nettyMessage.toBytes());
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeBytes("dn=EA99000001,url=http://www.baidu.com\r\n".getBytes());
        byteBuf.writeBytes("dn=EA99000002,url=http://www.baidu.com\r\n".getBytes());
        byte[] bytes = new byte[byteBuf.writerIndex()];
        byteBuf.readBytes(bytes);
        byteBuf.release();
        messageReport.setPayload(bytes);
        mqttProducer.publish("/sys/AE99000001/receive",messageReport);

        return "ok";
    }

}
