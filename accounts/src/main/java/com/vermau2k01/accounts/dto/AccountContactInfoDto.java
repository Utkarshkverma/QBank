package com.vermau2k01.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;


@ConfigurationProperties(prefix = "accounts")
public record AccountContactInfoDto(
        String message,
        ContactDetails contactDetails
) {

    public record ContactDetails(
            String name,
            String email,
            List<String> onCallService
    ) {
    }
}
