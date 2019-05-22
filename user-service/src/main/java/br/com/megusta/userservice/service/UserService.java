package br.com.megusta.userservice.service;

import br.com.megusta.userservice.builder.command.UserBuildCommand;
import br.com.megusta.userservice.exceptions.AuthenticationFailedException;
import br.com.megusta.userservice.exceptions.DataIntegrityException;
import br.com.megusta.userservice.exceptions.ObjectNotFoundException;
import br.com.megusta.userservice.model.domain.User;
import br.com.megusta.userservice.model.dto.UserDTO;
import br.com.megusta.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private AddressService addressService;
    @Autowired
    private UserBuildCommand userBuildCommand;

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
    public User updateUserAddress(User user){
        return save(user);
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
        user.setPassword(dto.getPassword());
        save(user);
    }

    public User saveUser(UserDTO dto){
        return save(userBuildCommand.execute(dto));
    }

}
