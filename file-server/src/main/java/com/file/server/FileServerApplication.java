package com.file.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.file.server.mapper")
public class FileServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileServerApplication.class,args);
    }
}
