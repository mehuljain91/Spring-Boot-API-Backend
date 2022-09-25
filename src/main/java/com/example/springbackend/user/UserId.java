package com.example.springbackend.user;

import com.example.orm.jpa.AbstractEntityId;
import java.util.UUID;

/**
 *
 * @author mehul jain
 */
public class UserId extends AbstractEntityId<UUID> {
    protected UserId() {
        
    }
    
    public UserId(UUID id) {
        super(id);
    }
}
