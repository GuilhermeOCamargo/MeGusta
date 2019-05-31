package br.com.megusta.userservice.repository;

import br.com.megusta.userservice.model.domain.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * @author Guilherme Camargo
 * */
public interface AddressRepository extends MongoRepository<Address, String> {
}
