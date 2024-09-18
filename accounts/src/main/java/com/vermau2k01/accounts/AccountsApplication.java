package com.vermau2k01.accounts;

import com.vermau2k01.accounts.dto.AccountContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(value = {AccountContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Account microservice REST API documentation",
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
@EnableJpaAuditing
public class AccountsApplication {

	public static void main(String[] args) {

		SpringApplication.run(AccountsApplication.class, args);
	}

}
