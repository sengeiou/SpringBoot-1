package com.onejane;

import com.onejane.jwt.utils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Auther: codewj
 * @Date: 2019/8/1 11:08
 * @Description:
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
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
}
