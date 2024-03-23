package vn.edu.ptit.sqa.service;

import vn.edu.ptit.sqa.dto.AuthResult;
import vn.edu.ptit.sqa.dto.LoginDTO;

public interface AuthService {
    AuthResult login(LoginDTO loginDTO);
}
