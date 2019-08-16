package br.com.lhamello.springbootpetshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients	
public class SpringBootPetshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPetshopApplication.class, args);
	}

}
