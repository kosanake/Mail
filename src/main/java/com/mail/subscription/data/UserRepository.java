package com.mail.subscription.data;

import com.mail.subscription.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * User repository for CRUD operations and queries
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
