package com.wes.webflux;

import java.util.Locale;

import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.ReactiveDatabaseSelectionProvider;
import org.springframework.data.neo4j.core.transaction.ReactiveNeo4jTransactionManager;
import org.springframework.data.neo4j.repository.config.ReactiveNeo4jRepositoryConfigurationExtension;
import org.springframework.transaction.ReactiveTransactionManager;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Spring Boot Application entrypoint.
 *
 * @author Andrea Cavallo
 * @param <FullLoggingInterceptor>
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.wes.webflux" })
@EnableCaching(proxyTargetClass = true)
public class WebfluxApplication{
   
	
	  
public static void main(String[] args) {
		Locale.setDefault(Locale.ITALY);
		SpringApplication application = new SpringApplication(WebfluxApplication.class);
		application.run(args);
	}

	// http://localhost:8080/webjars/swagger-ui/index.html?configUrl=/api-docs/swagger-config

	@Configuration
	public class OpenApiConfig {
		@Bean
		public OpenAPI topologyOpenAPI() {
			return new OpenAPI().components(new Components())
					.info(new Info().title("2WES API'S FOR TWITCH NEO4J").description("Welcome, To the Swagger, SWAG!"));
		}
	}

	@Bean(ReactiveNeo4jRepositoryConfigurationExtension.DEFAULT_TRANSACTION_MANAGER_BEAN_NAME)
	public ReactiveTransactionManager reactiveTransactionManager(Driver driver,
			ReactiveDatabaseSelectionProvider databaseNameProvider) {
		return new ReactiveNeo4jTransactionManager(driver, databaseNameProvider);
	}

}
