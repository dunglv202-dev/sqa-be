package vn.edu.ptit.sqa.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import vn.edu.ptit.sqa.config.SecurityConfig;
import vn.edu.ptit.sqa.dto.auth.AuthResult;
import vn.edu.ptit.sqa.dto.auth.LoginDTO;
import vn.edu.ptit.sqa.exception.UnauthenticatedException;
import vn.edu.ptit.sqa.helper.JwtProvider;
import vn.edu.ptit.sqa.model.ApplicationUser;
import vn.edu.ptit.sqa.service.AuthService;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    public AuthResult login(LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(),
                    loginDTO.getPassword()
                )
            );
            String accessToken = generateAccessToken(authentication);

            return AuthResult.builder()
                .displayName(((ApplicationUser) authentication.getPrincipal()).getUser().getDisplayName())
                .accessToken(accessToken)
                .build();
        } catch (BadCredentialsException e) {
            throw new UnauthenticatedException(e);
        }
    }

    private String generateAccessToken(Authentication authentication) {
        if (!authentication.isAuthenticated()) {
            throw new RuntimeException("Could not generate access token for unauthenticated instance");
        }

        ApplicationUser user = (ApplicationUser) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        claims.put(
            SecurityConfig.TOKEN_CLAIM_AUTHORITY,
            user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
        );

        return jwtProvider.generateToken(
            user.getUsername(),
            SecurityConfig.ACCESS_TOKEN_LIFETIME,
            claims
        );
    }
}
