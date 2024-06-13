package spring.boot.java_rest_api.UsersTest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.boot.java_rest_api.model.User;
import spring.boot.java_rest_api.model.dto.UserDTO;
import spring.boot.java_rest_api.service.IUserService;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UsersControllerTest {
    @Autowired
    private IUserService userService;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldReturnNullWhenNoExistsUsers() throws Exception {
        List<UserDTO> users = userService.findAll();
        assertThat(users.size()).isEqualTo(0);
    }

    @Test
    void shouldReturnNullWhenNameNoMatches() throws Exception {
        List<UserDTO> users = userService.findByNameLike("test");
        assertThat(users.size()).isEqualTo(0);
    }

    @Test
    void shouldReturnNullWhenSendUserInvalid() throws Exception {
        User user = new User();
        user.setName("jose carlos paez valdez");
        user.setUsername("");

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertThat(violations.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void shouldCreateNewUserWhenUserIsValid() throws Exception {
        User user = new User();
        user.setName("jose carlos paez valdez");
        user.setUsername("jcpv");

        UserDTO userDTO = userService.save(user);

        assertThat(userDTO).isNotNull();
    }

    @Test
    void shouldReturnUsersWhenExists() throws Exception {
        User user = new User();
        user.setName("jose carlos paez valdez");
        user.setUsername("jcpv1");

        userService.save(user);

        List<UserDTO> users = userService.findAll();

        assertThat(users.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void shouldReturnOkWhenGetUsersByNameAndMatches() throws Exception {
        User user = new User();
        user.setName("jose carlos paez valdez");
        user.setUsername("jcpv2");

        userService.save(user);

        List<UserDTO> users = userService.findByNameLike("jose");

        assertThat(users.size()).isGreaterThanOrEqualTo(1);
    }
}
