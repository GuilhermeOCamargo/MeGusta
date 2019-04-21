package br.com.megusta.userservice.service;

import br.com.megusta.userservice.builder.AddressBuilder;
import br.com.megusta.userservice.exceptions.DataIntegrityException;
import br.com.megusta.userservice.exceptions.ObjectNotFoundException;
import br.com.megusta.userservice.model.domain.Address;
import br.com.megusta.userservice.model.domain.User;
import br.com.megusta.userservice.model.dto.AddressDTO;
import br.com.megusta.userservice.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**
 * @author Guilherme Camargo
 * */
@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserService userService;

    public Address findById(Long id){
        Optional<Address> logradouro = addressRepository.findById(id);
        return logradouro.orElseThrow(() -> new ObjectNotFoundException("Endereço não encontrado."));
    }

    public Address save(Address address){
        try{
            return addressRepository.save(address);

        }catch (Exception e){
            throw new DataIntegrityException("Ocorreu um erro ao completar a ação.");
        }
    }

    public Address findByUser(Long usuarioId){
        User user = userService.findById(usuarioId);
        Optional<Address> logradouro = addressRepository.findByUser(user);
        return logradouro.orElseThrow(() -> new ObjectNotFoundException("Nenhum endereço encontrado."));
    }

    public Address saveAddress(AddressDTO dto){
        Address address = fromDto(dto);
        if(address.getUser() == null){
            User user = userService.findById(dto.getUserId());
            address.setUser(user);
            user.setAddress(address);

        }
        save(address);
        return address;
    }


    private Address fromDto(AddressDTO dto){
        if(dto.getId() == null){
            return new AddressBuilder().createFromDto(dto).build();
        }else{
            return new AddressBuilder(findById(dto.getId())).updateFromDto(dto).build();
        }
    }
}
