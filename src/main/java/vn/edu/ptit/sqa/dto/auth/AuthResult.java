package vn.edu.ptit.sqa.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthResult {
    private String displayName;
    private String accessToken;
}
