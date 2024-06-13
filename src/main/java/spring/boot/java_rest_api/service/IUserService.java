package spring.boot.java_rest_api.service;

import org.springframework.stereotype.Service;
import spring.boot.java_rest_api.model.User;
import spring.boot.java_rest_api.model.dto.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> findAll();
    List<UserDTO> findByNameLike(String name);
    UserDTO save(User user);
}
