package com.example.orm.jpa;

import java.util.UUID;

/**
 *
 * @author mehul jain
 */
public class InMemoryUniqueIdGenerator implements UniqueIdGenerator<UUID> {

    @Override
    public UUID getNextUniqueId() {
        return UUID.randomUUID();
    }
}
