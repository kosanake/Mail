package com.mail.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Super class for entities with surrogate ID
 */
@Data
@NoArgsConstructor
@MappedSuperclass
public class SuperClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
}
