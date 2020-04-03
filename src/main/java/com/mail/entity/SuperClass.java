package com.mail.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * Super class for entities with surrogate ID
 */
@Data
@MappedSuperclass
public abstract class SuperClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "Id required")
    protected Long id;
}
