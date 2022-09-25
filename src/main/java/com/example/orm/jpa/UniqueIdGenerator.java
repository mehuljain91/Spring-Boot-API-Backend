package com.example.orm.jpa;

/**
 *
 * @author mehul jain
 */
public interface UniqueIdGenerator<T> {

    T getNextUniqueId();
}
