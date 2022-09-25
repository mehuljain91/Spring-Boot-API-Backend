package com.example.springbackend.user;

import com.example.springbackend.user.UserId;
import com.example.springbackend.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

/**
 *
 * @author mehul jain
 */
public class Users {

    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public static final String AGENT_EMAIL = "agent@example.com";
    public static final String AGENT_PASSWORD = "agent";
    public static final String LEAD_EMAIL = "lead@example.com";
    public static final String LEAD_PASSWORD = "lead";

    private static User AGENT = User.createAgent(newRandomId(),
            AGENT_EMAIL,
            PASSWORD_ENCODER.encode(AGENT_PASSWORD));

    private static User LEAD = User.createLead(newRandomId(),
            LEAD_EMAIL,
            PASSWORD_ENCODER.encode(LEAD_PASSWORD));

    public static UserId newRandomId() {
        return new UserId(UUID.randomUUID());
    }

    public static User newRandomAgent() {
        return newRandomAgent(newRandomId());
    }

    public static User newRandomAgent(UserId userId) {
        String uniqueId = userId.asString().substring(0, 5);
        return User.createAgent(userId,
                "user-" + uniqueId
                + "@example.com",
                PASSWORD_ENCODER.encode("user"));
    }

    public static User agent() {
        return AGENT;
    }

    public static User lead() {
        return LEAD;
    }

    private Users() {
    }
}
