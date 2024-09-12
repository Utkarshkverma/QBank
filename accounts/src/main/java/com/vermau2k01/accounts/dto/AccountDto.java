package com.vermau2k01.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Account",
        description = "Schema to hold the customer information of the customer"
)
@Data
public class AccountDto {
    @NotEmpty(message = "Account number cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    @Schema(
            description = "Account Number of Eazy Bank account",
            example = "3454433243"
    )
    private Long accountNumber;

    @NotEmpty(message = "Account type can not be a null or empty")
    @Schema(
            description = "Account type of Eazy Bank account",
            example = "Savings"
    )
    private String accountType;

    @NotEmpty(message = "Branch address can not be a null or empty")
    @Schema(
            description = "Eazy Bank branch address",
            example = "123 NewYork"
    )
    private String branchAddress;
}
