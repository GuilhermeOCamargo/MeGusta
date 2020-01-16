package br.com.megusta.userservice.service;

import br.com.megusta.userservice.exceptions.AuthenticationFailedException;
import br.com.megusta.userservice.exceptions.DataIntegrityException;
import br.com.megusta.userservice.exceptions.ObjectNotFoundException;
import br.com.megusta.userservice.model.domain.Address;
import br.com.megusta.userservice.model.domain.User;
import br.com.megusta.userservice.model.dto.AddressDTO;
import br.com.megusta.userservice.model.dto.UserDTO;
import br.com.megusta.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Guilherme Camargo
 * */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public User authenticate(String email, String password){
        Optional<User> usuario = userRepository.findByEmailAndPassword(email, password);
        return usuario.orElseThrow(() -> new AuthenticationFailedException("Email ou senha incorretos."));
    }

    public User findById(String id){
        Optional<User> usuario = userRepository.findById(id);
        return usuario.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    public User findByEmail(String email){
        Optional<User> usuario = userRepository.findByEmail(email);
        return usuario.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }
    public List<UserDTO> findAll(){
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(user))
                .collect(Collectors.toList());
    }

    private User save(User user){
        try{
            return userRepository.save(user);
        }catch (Exception e){
            throw new DataIntegrityException("Ocorreu um erro ao completar a ação.");
        }
    }

    public void updatePassword(UserDTO dto){
        User user = findById(dto.getId());
        user.updatePassword(passwordEncoder.encode(dto.getPassword()));
        save(user);
    }

    public User saveUser(UserDTO dto){
        return save(convertDtoToEntity(dto));
    }

    public void addAddress(AddressDTO addressDTO, String userId){
        User user = findById(userId);
        Address address = addressService.saveAddress(addressDTO);
        user.getAddresses().add(address);
        save(user);
    }

    public void removeAddress(String userId, String addressId){
        User user = findById(userId);
        Address addressToRemove = addressService.findById(addressId);
        user.getAddresses().forEach((address) -> {
            if(address.equals(addressToRemove)){
                user.getAddresses().remove(address);
            }
        });
        save(user);
        addressService.delete(addressId);
    }

    private User convertDtoToEntity(UserDTO dto){
        return User.builder()
                .withName(dto.getName())
                .withPassword(passwordEncoder.encode(dto.getPassword()))
                .withEmail(dto.getEmail())
                .withId(dto.getId())
                .build();
    }

}
