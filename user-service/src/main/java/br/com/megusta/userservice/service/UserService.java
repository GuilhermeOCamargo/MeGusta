package br.com.megusta.userservice.service;

import br.com.megusta.userservice.builder.UserBuilder;
import br.com.megusta.userservice.exceptions.AuthenticationFailedException;
import br.com.megusta.userservice.exceptions.DataIntegrityException;
import br.com.megusta.userservice.exceptions.ObjectNotFoundException;
import br.com.megusta.userservice.model.domain.Address;
import br.com.megusta.userservice.model.domain.User;
import br.com.megusta.userservice.model.dto.AddressDTO;
import br.com.megusta.userservice.model.dto.UserDTO;
import br.com.megusta.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Guilherme Camargo
 * */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LogradouroService logradouroService;

    public User authenticate(String email, String password){
        Optional<User> usuario = userRepository.findByEmailAndPassword(email, password);
        return usuario.orElseThrow(() -> new AuthenticationFailedException("Email ou senha incorretos."));
    }

    public User findById(Long id){
        Optional<User> usuario = userRepository.findById(id);
        return usuario.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    public User findByEmail(String email){
        Optional<User> usuario = userRepository.findByEmail(email);
        return usuario.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    public User findByIdAndEmail(Long id, String email){
        Optional<User> user = userRepository.findByIdAndEmail(id, email);
        return user.orElseThrow(() -> new ObjectNotFoundException(("Usuário não encontrado.")));
    }

    private User save(User user){
        try{
            return userRepository.save(user);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Email já cadastrado.");
        }
    }

    public void updatePassword(UserDTO dto){
        User user = findById(dto.getId());
        user.setPassword(dto.getPassword());
        save(user);
    }

    public void addLogradouro(AddressDTO addressDTO, Long usuarioId){
        Address address = logradouroService.findOrPopulate(addressDTO);
        User user = findById(usuarioId);
        address.setUser(user);
        address = logradouroService.save(address);
        user.setAddress(address);
        save(user);
    }

    public User saveUser(UserDTO dto){
        return save(fromDto(dto));
    }

     /** Convert a {@link UserDTO} in a {@link User}
     * @param dto
     * @return User
     * */
    public User fromDto(UserDTO dto){
        User user;
        if(dto.getId() != null){
            user = new UserBuilder(findById(dto.getId())).updateObject(dto).build();
        }else{
            user = new UserBuilder().createFromDto(dto).build();
        }
        return user;
    }
}
