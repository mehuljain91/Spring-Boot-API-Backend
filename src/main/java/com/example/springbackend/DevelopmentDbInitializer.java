package com.example.springbackend;

import com.example.springbackend.infrastructure.SpringProfiles;
import com.example.springbackend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 *
 * @author mehul jain
 */
@Component
@Profile(SpringProfiles.DEV)
public class DevelopmentDbInitializer implements ApplicationRunner {

    private final UserService userService;

    @Autowired
    public DevelopmentDbInitializer(UserService userService) { 
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) { 
        createTestUsers();
    }

    private void createTestUsers() {
        userService.createAgent("agent@example.com", "agent"); 
    }
}
