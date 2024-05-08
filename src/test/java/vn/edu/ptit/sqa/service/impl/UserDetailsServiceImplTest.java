package vn.edu.ptit.sqa.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import vn.edu.ptit.sqa.entity.auth.User;
import vn.edu.ptit.sqa.model.ApplicationUser;
import vn.edu.ptit.sqa.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserDetailsServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername() {
        String username = "username";
        User user = new User();
        user.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        ApplicationUser result = (ApplicationUser) userDetailsService.loadUserByUsername(username);

        assertEquals(username, result.getUser().getUsername());
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        String username = "testuser";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(
            UsernameNotFoundException.class,
            () -> userDetailsService.loadUserByUsername(username)
        );
    }
}