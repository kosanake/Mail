package com.mail.data;

import com.mail.entity.Subscription;
import com.mail.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Subscription repository for CRUD operations and queries
 */
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
    List<Subscription> findByUserOrderByDateDesc(User user);
}
