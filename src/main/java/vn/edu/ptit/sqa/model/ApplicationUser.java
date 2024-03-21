package vn.edu.ptit.sqa.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.edu.ptit.sqa.entity.Authority;
import vn.edu.ptit.sqa.entity.User;

import java.util.Collection;
import java.util.List;

@Getter
public class ApplicationUser implements UserDetails {
    private User user;

    public static ApplicationUser ofUser(User user) {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.user = user;
        return applicationUser;
    }

    public static ApplicationUser of(Long userId, Collection<String> authorities) {
        User user = new User();
        user.setId(userId);
        user.setAuthorities(authorities.stream().map(Authority::new).toList());
        return ApplicationUser.ofUser(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities()
            .stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getCode()))
            .toList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
