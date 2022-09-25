package com.example.springbackend.infrastructure.security;

import com.example.springbackend.infrastructure.security.ApplicationUserDetails;
import com.example.springbackend.infrastructure.security.ApplicationUserDetailsService;
import com.example.springbackend.user.UserRepository;
import com.example.springbackend.user.Users;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author mehul jain
 */
public class ApplicationUserDetailsServiceTest {

    @Test
    public void givenExistingUsername_whenLoadingUser_userIsReturned() {
        UserRepository repository = mock(UserRepository.class);
        ApplicationUserDetailsService service = new ApplicationUserDetailsService(repository);
        when(repository.findByEmailIgnoreCase(Users.AGENT_EMAIL))
                .thenReturn(Optional.of(Users.agent()));

        UserDetails userDetails = service.loadUserByUsername(Users.AGENT_EMAIL);
        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo(Users.AGENT_EMAIL);
        assertThat(userDetails.getAuthorities()).extracting(GrantedAuthority::getAuthority)
                .contains("ROLE_AGENT");
        assertThat(userDetails).isInstanceOfSatisfying(ApplicationUserDetails.class,
                applicationUserDetails -> {
                    assertThat(applicationUserDetails.getUserId())
                            .isEqualTo(Users.agent().getId());
                });
    }

    @Test(expected = UsernameNotFoundException.class)
    public void givenNotExistingUsername_whenLoadingUser_exceptionThrown() {
        UserRepository repository = mock(UserRepository.class);
        ApplicationUserDetailsService service = new ApplicationUserDetailsService(repository);
        when(repository.findByEmailIgnoreCase(anyString())).thenReturn(Optional.empty());

        service.loadUserByUsername("i@donotexist.com");
    }
}
