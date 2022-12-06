package com.itemis.coding.challenge.taxes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaxesApplication implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaxesApplication.class);
	private final DemoReceipts demoReceipts;

	public TaxesApplication(DemoReceipts demoReceipts) {
		this.demoReceipts = demoReceipts;
	}

	public static void main(String[] args) {
		SpringApplication.run(TaxesApplication.class, args);
	}

	@Override
	public void run(String... args) {
		LOGGER.info("Application is started!");
		demoReceipts.runApp();
		LOGGER.info("Application is closed!");
	}
}
