package com.example.springbackend.user;

import com.example.springbackend.user.UserRepository;
import com.example.springbackend.user.UserRole;
import com.example.springbackend.user.User;
import com.example.orm.jpa.InMemoryUniqueIdGenerator;
import com.example.orm.jpa.UniqueIdGenerator;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mehul jain
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void TestStoreUser() {
        HashSet<UserRole> roles = new HashSet<>();
        roles.add(UserRole.AGENT);
        User user = repository.save(new User(repository.nextId(), "mycheckemail@gmail.com", "mypasswordcheck", roles));
        assertThat(user).isNotNull();
        assertThat(repository.count()).isEqualTo(1L);
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        public UniqueIdGenerator<UUID> generator() {
            return new InMemoryUniqueIdGenerator();
        }
    }

    @Test
    public void testFindByEmail() {
        User user = Users.newRandomAgent();
        repository.save(user);
        Optional<User> optional = repository.findByEmailIgnoreCase(user.getEmail());
        assertThat(optional).isNotEmpty()
                .contains(user);
    }

    @Test
    public void testFindByEmailIgnoringCase() {
        User user = Users.newRandomAgent();
        repository.save(user);
        Optional<User> optional = repository.findByEmailIgnoreCase(user.getEmail()
                .toUpperCase(Locale.US));
        assertThat(optional).isNotEmpty()
                .contains(user);
    }

    @Test
    public void testFindByEmail_unknownEmail() {
        User user = Users.newRandomAgent();
        repository.save(user);
        Optional<User> optional = repository.findByEmailIgnoreCase("will.not@find.me");
        assertThat(optional).isEmpty();
    }
}
