package it.woodcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration(proxyBeanMethods = false)
public class WoodCastApplication {

	public static void main(String[] args) {
		SpringApplication.run(WoodCastApplication.class, args);
	}

}
