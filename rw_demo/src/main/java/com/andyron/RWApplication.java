package com.andyron;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author andyron
 **/
@SpringBootApplication
@Slf4j
public class RWApplication {
    public static void main(String[] args) {
        SpringApplication.run(RWApplication.class, args);
        log.info("项目启动成功...");
    }
}
