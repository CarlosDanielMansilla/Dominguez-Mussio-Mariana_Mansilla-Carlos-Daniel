package com.backend.IntegradorFinal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntegradorFinalApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(IntegradorFinalApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(IntegradorFinalApplication.class, args);
		LOGGER.info("Proyecto integrador is now running ...");
	}

}
