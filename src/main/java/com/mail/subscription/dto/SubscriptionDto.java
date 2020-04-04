package com.mail.subscription.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * DTO for subscription entity
 */
@Data
public class SubscriptionDto {
    @NotBlank(message = "Subscriber name required")
    private String name;

    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Wrong email format")
    private String email;
}
