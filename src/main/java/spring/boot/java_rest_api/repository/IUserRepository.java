package spring.boot.java_rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.boot.java_rest_api.model.User;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {
    @Query("SELECT U FROM User U WHERE U.name LIKE %?1%")
    List<User> findByNameLike(String name);
}
