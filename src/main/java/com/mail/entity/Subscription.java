package com.mail.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.Instant;

/**
 * Represents subscription entry consisting of subscriber name, email and creation date.
 * Username field is the name of login user {@link org.springframework.security.core.userdetails.User} represents tenant identifier
 */
@Data
@NoArgsConstructor
@Entity
public class Subscription extends SuperClass {

    @NotNull(message = "Login name required")
    private String username;

    @NotBlank(message = "Subscriber name required")
    private String name;

    @NotBlank(message = "Email required")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Wrong email format")
    private String email;

    @NotNull(message = "Date required")
    private Instant date;
}
