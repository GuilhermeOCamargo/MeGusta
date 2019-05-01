package br.com.megusta.userservice.repository;

import br.com.megusta.userservice.model.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Guilherme Camargo
 * */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Transactional(readOnly = true)
    Optional<User> findByEmailAndPassword(String email, String password);
    @Transactional(readOnly = true)
    Optional<User> findByEmail(String email);

}
