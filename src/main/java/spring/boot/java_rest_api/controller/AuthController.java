package spring.boot.java_rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.java_rest_api.model.Token;
import spring.boot.java_rest_api.service.IAuthService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping(value = "/token")
    public ResponseEntity<?> getToken(Authentication authentication) {
        String token = authService.generateToken(authentication);

        Token objToken = new Token();
        objToken.setToken(token);

        return ResponseEntity.status(HttpStatus.OK).body(objToken);
    }
}
