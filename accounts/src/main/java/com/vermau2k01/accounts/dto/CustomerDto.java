package com.vermau2k01.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold the customer information"
)
public class CustomerDto {

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 5, max = 30, message = "name should be between 5-30 characters")
    @Schema(
            description = "Name of the customer",
            example = "Utkarsh Kumar Verma"
    )
    private String name;

    @NotEmpty(message = "email address cannot be empty")
    @Email(message = "email is not well formatted")
    @Schema(
            description = "Email of the customer",
            example = "vermau2k01@gmail.com"
    )
    private String email;


    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    @Schema(
            description = "Mobile number of the customer",
            example = "9905893940"
    )
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )
    private AccountDto accountDto;
}
