package com.ctlfab.estatehandle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;

@SpringBootApplication
@RestController
public class EstateHandleApplication{
    public static void main(String[] args) {
        SpringApplication.run(EstateHandleApplication.class, args);
    }

    @Bean
    public HttpClient getHttpClient() { return HttpClient.newHttpClient();}
}
