package com.onejane;

import com.onejane.jwt.utils.JwtUtil;
import com.onejane.websocket.ChatRoomServerEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Auther: codewj
 * @Date: 2019/8/1 11:08
 * @Description:
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableWebSocket
@EnableScheduling
public class StartApplication {
    public static void main(final String... args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @Bean        // 配置bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }

    /**
     * 注册 ServerEndpointExporter Bean对象（因为Springboot没有自动注册，所以得手动注册）
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    /**
     * 注册 端点对象
     */
    @Bean
    public ChatRoomServerEndpoint chatRoomServerEndpoint(){
        return new ChatRoomServerEndpoint();
    }
}
