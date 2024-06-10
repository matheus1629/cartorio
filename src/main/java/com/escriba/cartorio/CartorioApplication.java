package com.escriba.cartorio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.hibernate.boot.cfgxml.spi.LoadedConfig.baseline;

@SpringBootApplication
public class CartorioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartorioApplication.class, args);
	}

}
