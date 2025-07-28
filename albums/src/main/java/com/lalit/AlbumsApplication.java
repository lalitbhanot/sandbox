package com.lalit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlbumsApplication
{
    public static void main(String[] args) {
        SpringApplication.run(AlbumsApplication.class, args);
    }
}
