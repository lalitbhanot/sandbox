package com.lalit.configuration;

import java.io.IOException;

import com.lalit.actuator.FootballCustomEndpoint;
import com.lalit.service.FileLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FootballConfiguration {
    
    @Value("${football.folder}")
    private String folder;

    private static final Logger logger = LoggerFactory.getLogger(FootballConfiguration.class);

    @Bean
    public FileLoader fileLoader() throws IOException{
        logger.info("Football folder value: {}", folder);
        System.out.println("Football folder value: " + folder);
        return new FileLoader(folder);
    }

    @Bean
    public FootballCustomEndpoint footballCustomEndpoint(FileLoader fileLoader){
        return new FootballCustomEndpoint(fileLoader);
    }
}
