package spring.boot.java_rest_api.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.java_rest_api.model.User;
import spring.boot.java_rest_api.model.dto.UserDTO;
import spring.boot.java_rest_api.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<UserDTO> findAll() {
        try {
            List<User> users = userRepository.findAll();
            List<UserDTO> usersDTO = new ArrayList<>();

            users.forEach(user -> {
                UserDTO userDTO = new UserDTO();
                userDTO.setName(user.getName());
                userDTO.setUsername(user.getUsername());
                usersDTO.add(userDTO);
            });

            return usersDTO;
        }
        catch (Exception e) {
            throw new RuntimeException("Error to try getting all users", e);
        }
    }

    @Override
    public List<UserDTO> findByNameLike(String name) {
        try {
            List<User> users = userRepository.findByNameLike(name);
            List<UserDTO> usersDTO = new ArrayList<>();

            users.forEach(user -> {
                UserDTO userDTO = new UserDTO();
                userDTO.setName(user.getName());
                userDTO.setUsername(user.getUsername());

                usersDTO.add(userDTO);
            });

            return usersDTO;
        }
        catch (Exception e) {
            throw new RuntimeException("Error to try getting users by name", e);
        }
    }

    @Override
    public UserDTO save(User user) {
        try {
            user.setName(user.getName().toLowerCase());

            User newUser = userRepository.save(user);

            UserDTO userDTO = new UserDTO();
            userDTO.setName(newUser.getName());
            userDTO.setUsername(newUser.getUsername());

            return userDTO;
        }
        catch (Exception e) {
            throw new RuntimeException("Error to try save user", e);
        }
    }
}
