package de.codecentric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * Spring-boot application to start. Runs camel infinitely. Can be stopped via shortcut
 * Ctrl+C
 * 
 */
@SpringBootApplication
public class CepExampleApplication {

	/**
	 * starts the spring-boot application.
	 * @param args arguments for spring-boot
	 */
	public static void main(String[] args) {
		new SpringApplication(CepExampleApplication.class).run(args);
	}
}
