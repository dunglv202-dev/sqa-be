package vn.edu.ptit.sqa.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import vn.edu.ptit.sqa.dto.auth.AuthResult;
import vn.edu.ptit.sqa.dto.auth.LoginDTO;
import vn.edu.ptit.sqa.entity.auth.Authority;
import vn.edu.ptit.sqa.entity.auth.User;
import vn.edu.ptit.sqa.exception.UnauthenticatedException;
import vn.edu.ptit.sqa.helper.JwtProvider;
import vn.edu.ptit.sqa.model.ApplicationUser;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtProvider jwtProvider;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_ValidCredentials_ReturnsAuthResult() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("username");
        loginDTO.setPassword("password");
        Authentication authentication = mock(Authentication.class);
        ApplicationUser applicationUser = mock(ApplicationUser.class);
        User user = mock(User.class);
        Authority authority = new Authority("ROLE_USER");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
            .thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn(applicationUser);
        when(applicationUser.getUser()).thenReturn(user);
        when(user.getDisplayName()).thenReturn("John Doe");
        when(user.getAuthorities()).thenReturn(Collections.singletonList(authority));
        when(jwtProvider.generateToken(any(), any(), anyMap())).thenReturn("access_token");

        // Act
        AuthResult authResult = authService.login(loginDTO);

        // Assert
        assertNotNull(authResult);
        assertEquals("John Doe", authResult.getDisplayName());
        assertEquals(Collections.singletonList("ROLE_USER"), authResult.getAuthorities());
        assertEquals("access_token", authResult.getAccessToken());

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void login_InvalidCredentials_ThrowsUnauthenticatedException() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("username");
        loginDTO.setPassword("password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
            .thenThrow(BadCredentialsException.class);

        // Act & Assert
        assertThrows(UnauthenticatedException.class, () -> authService.login(loginDTO));

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }
}