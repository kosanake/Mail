package com.mail.subscription.service;

import com.mail.subscription.data.SubscriptionRepository;
import com.mail.subscription.dto.SubscriptionDto;
import com.mail.subscription.dto.mapper.SubscriptionMapper;
import com.mail.subscription.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

    private SubscriptionRepository subscriptionRepository;
    private SubscriptionMapper subscriptionMapper;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, SubscriptionMapper subscriptionMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    /**
     * Create and persist subscription entity from subscriptionDto
     * @param subscriptionDto subscription dto
     */
    public Subscription createSubscription(SubscriptionDto subscriptionDto) {
        Subscription subscription = subscriptionMapper.subscriptionDtoToSubscription(subscriptionDto);
        return subscriptionRepository.save(subscription);
    }
}
