package be.thomasmore.microservice.user.repository;

import be.thomasmore.microservice.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface UserRepository extends JpaRepository<User, Integer>{
    List<User> findUserByEmail(@Param("email") String email);

    List<User> findUserById(@Param("id") Integer id);
}
