package br.com.megusta.userservice.service;

import br.com.megusta.userservice.exceptions.ObjectNotFoundException;
import br.com.megusta.userservice.model.domain.Address;
import br.com.megusta.userservice.model.domain.User;
import br.com.megusta.userservice.model.dto.AddressDTO;
import br.com.megusta.userservice.repository.LogradouroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**
 * @author Guilherme Camargo
 * */
@Service
public class LogradouroService {

    @Autowired
    private LogradouroRepository logradouroRepository;
    @Autowired
    private UserService userService;

    public Address findById(Long id){
        Optional<Address> logradouro = logradouroRepository.findById(id);
        return logradouro.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. Id:"+id+
                "Tipo: "+ Address.class.getSimpleName()));
    }

    public Address save(Address address){
        return logradouroRepository.save(address);
    }

    public Address findByUsuario(Long usuarioId){
        User user = userService.findById(usuarioId);
        Optional<Address> logradouro = logradouroRepository.findByUser(user);
        return logradouro.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. UsuarioId:"+usuarioId+
                "Tipo: "+ Address.class.getSimpleName()));
    }

    public Address findOrPopulate(AddressDTO dto){
        Address address = null;
        try{
            if(dto.getId() != null){
                address = findById(dto.getId());
            }else{
                address = fromDto(dto);
            }
        }catch (ObjectNotFoundException e){
            address = fromDto(dto);
        }
        return address;
    }

    private Address fromDto(AddressDTO dto){
        return new Address(dto.getId(), dto.getStreetName(), dto.getNumber(), dto.getComplement(), dto.getZipCode());
    }
}
