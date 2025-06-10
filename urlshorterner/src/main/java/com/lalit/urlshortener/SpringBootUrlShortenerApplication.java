package com.lalit.urlshortener ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
// added for reading AppProperties file
@ConfigurationPropertiesScan
public class SpringBootUrlShortenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUrlShortenerApplication.class, args);
	}

}
