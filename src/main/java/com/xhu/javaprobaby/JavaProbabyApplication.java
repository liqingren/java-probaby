package com.xhu.javaprobaby;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xhu")
public class JavaProbabyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaProbabyApplication.class, args);
    }

}
