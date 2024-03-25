package vn.edu.ptit.sqa.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AuthResult {
    private String displayName;
    private List<String> authorities;
    private String accessToken;
}
