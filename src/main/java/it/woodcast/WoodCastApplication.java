package it.woodcast;

import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@SpringBootApplication
@Configuration(proxyBeanMethods = false)
@MultipartConfig
public class WoodCastApplication {

	public static void main(String[] args) {
		SpringApplication.run(WoodCastApplication.class, args);
	}


}
