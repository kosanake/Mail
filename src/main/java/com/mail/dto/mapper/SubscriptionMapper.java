package com.mail.dto.mapper;


import com.mail.dto.SubscriptionDto;
import com.mail.entity.Subscription;
import com.mail.entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Instant;

/**
 * Subscription mapper
 */
@Mapper
public interface SubscriptionMapper {
    Subscription subscriptionDtoToSubscription(SubscriptionDto subscriptionDto);
    SubscriptionDto subscriptionToSubscriptionDto(Subscription subscription);

    @AfterMapping
    default void afterMapping(@MappingTarget Subscription subscription) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        subscription.setUser(user);
        subscription.setDate(Instant.now());
    }
}
