package br.com.megusta.userservice.repository;

import br.com.megusta.userservice.model.domain.Address;
import br.com.megusta.userservice.model.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
/**
 * @author Guilherme Camargo
 * */
public interface AddressRepository extends CrudRepository<Address, Long> {

    @Transactional(readOnly = true)
    Optional<Address> findByUser(User user);
}
