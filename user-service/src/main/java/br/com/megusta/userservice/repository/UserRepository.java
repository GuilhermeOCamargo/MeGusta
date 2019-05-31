package br.com.megusta.userservice.repository;

import br.com.megusta.userservice.model.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Guilherme Camargo
 * */
public interface UserRepository extends MongoRepository<User, String> {

    @Transactional(readOnly = true)
    Optional<User> findByEmailAndPassword(String email, String password);
    @Transactional(readOnly = true)
    Optional<User> findByEmail(String email);

}
