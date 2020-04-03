package com.mail.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.Instant;

/**
 * Represents subscription entry consisting of subscriber name, email and creation date.
 * Username field is the login user {@link User} represents tenant identifier
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class Subscription extends SuperClass {

    @ManyToOne
    @NotNull(message = "User required")
    private User user;

    @NotBlank(message = "Subscriber name required")
    private String name;

    @NotBlank(message = "Email required")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Wrong email format")
    private String email;

    @NotNull(message = "Date required")
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant date;
}
