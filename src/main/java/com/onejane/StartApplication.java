package com.onejane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

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
}
