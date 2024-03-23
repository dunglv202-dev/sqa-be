package vn.edu.ptit.sqa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.sqa.dto.auth.AuthResult;
import vn.edu.ptit.sqa.dto.auth.LoginDTO;
import vn.edu.ptit.sqa.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @PreAuthorize("isAnonymous()")
    public AuthResult login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }
}
