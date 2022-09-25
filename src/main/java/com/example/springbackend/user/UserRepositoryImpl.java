package com.example.springbackend.user;

import com.example.orm.jpa.UniqueIdGenerator;
import java.util.UUID;

/**
 *
 * @author mehul jain
 */
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final UniqueIdGenerator<UUID> generator;

    public UserRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public UserId nextId() {
        return new UserId(generator.getNextUniqueId());
    }
}
