package tech.ericasoares.arquivaria.repository;

import org.springframework.data.repository.CrudRepository;
import tech.ericasoares.arquivaria.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUsername(String username);
}
