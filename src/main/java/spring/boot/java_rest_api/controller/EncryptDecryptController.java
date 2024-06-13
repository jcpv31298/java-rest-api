package spring.boot.java_rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.java_rest_api.service.IEncryptDecryptService;

@RestController
@RequestMapping(value = "/encryptdecrypt")
public class EncryptDecryptController {
    @Autowired
    private IEncryptDecryptService encryptDecryptService;

    @GetMapping("/encrypt/{value}")
    public ResponseEntity<?> encrypt(@PathVariable String value) {
        return ResponseEntity.status(HttpStatus.OK).body(encryptDecryptService.encrypt(value));
    }

    @GetMapping("/decrypt/{value}")
    public ResponseEntity<?> decrypt(@PathVariable String value) {
        return ResponseEntity.status(HttpStatus.OK).body(encryptDecryptService.decrypt(value));
    }
}
