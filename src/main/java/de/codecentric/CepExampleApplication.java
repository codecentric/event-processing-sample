package de.codecentric;

import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * 
 * Springboot application to start. Runs camel infinitely. Can be stopped via shortcut
 * Ctrl+C
 * 
 */
@SpringBootApplication
public class CepExampleApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplication(CepExampleApplication.class).run(args);

		CamelSpringBootApplicationController applicationController = applicationContext
				.getBean(CamelSpringBootApplicationController.class);

		applicationController.blockMainThread();
	}
}
