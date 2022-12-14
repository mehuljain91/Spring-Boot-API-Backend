package com.example.springbackend.user;

import com.example.orm.jpa.AbstractEntity;
import com.google.common.collect.Sets;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 *
 * @author mehul jain
 */

@Entity
@Table(name = "backend_user")
public class User extends AbstractEntity<UserId> {

    private String email;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Set<UserRole> roles;

    protected User() {

    }

    public User(UserId id, String email, String password, Set<UserRole> roles) {
        super(id);
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public static User createAgent(UserId userId, String email, String encodedPassword) {
        return new User(userId, email, encodedPassword, Sets.newHashSet(UserRole.AGENT));
    }

    public static User createLead(UserId userId, String email, String encodedPassword) {
        return new User(userId, email, encodedPassword, Sets.newHashSet(UserRole.LEAD));
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }
}
