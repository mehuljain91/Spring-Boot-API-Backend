package com.example.orm.jpa;

/**
 *
 * @author mehul jain
 */
public interface Entity<T extends EntityId> {
    T getId();
}
