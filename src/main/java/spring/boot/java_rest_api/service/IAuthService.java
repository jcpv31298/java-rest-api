package spring.boot.java_rest_api.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

public interface IAuthService {
    String generateToken(Authentication authentication);
}
