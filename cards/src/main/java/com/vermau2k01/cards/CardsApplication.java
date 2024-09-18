package com.vermau2k01.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API documentation",
				description = "This is a microservices project",
				version = "v1",
				contact = @Contact(
						name =  "Utkarsh Kumar Verma",
						email = "vermau2k01@gmail.com",
						url = ""
				),
				license = @License(
						name = "Apache 2.0",
						url = ""
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "",
				url = ""

		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
