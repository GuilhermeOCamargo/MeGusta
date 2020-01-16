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

        User user = User.builder()
                .withName("Guilherme")
                .withEmail("Guilherme@megusta.com")
                .withPassword("123456789")
                .build();
        user = userRepository.save(user);

        Address address = Address.builder()
                .withStreetName("rua 1")
                .withNumber("123")
                .withComplement(null)
                .withZipCode("00000000")
                .withName("casa")
                .build();
        address = addressRepository.save(address);

        user.getAddresses().add(address);
        userRepository.save(user);
    }
}
