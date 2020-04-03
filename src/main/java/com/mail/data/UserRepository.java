package com.mail.data;

import com.mail.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * User repository for CRUD operations and queries
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
