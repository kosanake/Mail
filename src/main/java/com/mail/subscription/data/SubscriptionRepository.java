package com.mail.subscription.data;

import com.mail.subscription.model.Subscription;
import com.mail.subscription.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Subscription repository for CRUD operations and queries
 */
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
    List<Subscription> findByUserOrderByDateDesc(User user);
}
