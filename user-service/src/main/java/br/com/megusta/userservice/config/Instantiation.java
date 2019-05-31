package br.com.megusta.userservice.config;

import br.com.megusta.userservice.model.domain.Address;
import br.com.megusta.userservice.model.domain.User;
import br.com.megusta.userservice.repository.AddressRepository;
import br.com.megusta.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * @author Guilherme Camargo
 * */
@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void run(String... args) throws Exception {
        addressRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setName("Guilherme");
        user.setEmail("guilherme@megusta.com");
        user.setPassword("123456789");
        user = userRepository.save(user);

        Address address = new Address("rua 1", "123", null, "00000000", "casa");
        address = addressRepository.save(address);

        user.getAddresses().add(address);
        userRepository.save(user);
    }
}
