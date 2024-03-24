package vn.edu.ptit.sqa.helper;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import vn.edu.ptit.sqa.entity.auth.User;
import vn.edu.ptit.sqa.exception.UnauthenticatedException;
import vn.edu.ptit.sqa.model.ApplicationUser;

@Component
public class AuthHelper {
    public User getSignedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new UnauthenticatedException();
        }

        return ((ApplicationUser) authentication.getPrincipal()).getUser();
    }
}
