package spring.boot.java_rest_api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.boot.java_rest_api.model.User;
import spring.boot.java_rest_api.model.dto.UserDTO;
import spring.boot.java_rest_api.service.IUserService;
import spring.boot.java_rest_api.validation.Validation;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UsersController {
    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<?> getUsers() {
        List<UserDTO> users = userService.findAll();

        if(users.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<?> getUsersByName(@PathVariable String name) {
        List<UserDTO> users = userService.findByNameLike(name);

        if(users.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result) {
        if(result.hasErrors()) {
            Map<String, String> errors = Validation.validation(result);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
}
