package vn.edu.ptit.sqa.filter;

import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import vn.edu.ptit.sqa.helper.JwtProvider;
import vn.edu.ptit.sqa.model.ApplicationUser;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFiler extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    @SuppressWarnings("NullableProblems")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // validate token if exists
        String jwtToken = getToken(request);
        if (jwtToken == null) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            Claims claims = jwtProvider.verifyToken(jwtToken);
            // set authentication context
            @SuppressWarnings("unchecked")
            Collection<String> authorities = (Collection<String>) claims.get("authorities");
            Authentication authentication = new PreAuthenticatedAuthenticationToken(
                ApplicationUser.of(Long.parseLong(claims.getSubject()), authorities),
                null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            log.error("Couldn't verify token");
        } finally {
            // continue with filter chain
            filterChain.doFilter(request, response);
        }
    }

    private String getToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        } else {
            return null;
        }
    }
}
