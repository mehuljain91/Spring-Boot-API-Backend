package com.example.springbackend.user;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

/**
 *
 * @author mehul jain
 */
public interface UserRepository extends CrudRepository<User, UUID>, UserRepositoryCustom {

    Optional<User> findByEmailIgnoreCase(String email);
}
