package vn.edu.ptit.sqa.service;

import vn.edu.ptit.sqa.dto.auth.AuthResult;
import vn.edu.ptit.sqa.dto.auth.LoginDTO;

public interface AuthService {
    AuthResult login(LoginDTO loginDTO);
}
