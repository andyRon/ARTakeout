package com.andyron.takeout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author andyron
 **/
@Slf4j  // 可以直接在log这个变量
@SpringBootApplication
public class ARTakeoutApplication {
    public static void main(String[] args) {
        SpringApplication.run(ARTakeoutApplication.class, args);
        log.info("项目启动成功....");
    }
}
