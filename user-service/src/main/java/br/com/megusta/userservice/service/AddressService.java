package br.com.megusta.userservice.service;

import br.com.megusta.userservice.builder.command.AddressBuildCommand;
import br.com.megusta.userservice.exceptions.DataIntegrityException;
import br.com.megusta.userservice.exceptions.ObjectNotFoundException;
import br.com.megusta.userservice.model.domain.Address;
import br.com.megusta.userservice.model.domain.User;
import br.com.megusta.userservice.model.dto.AddressDTO;
import br.com.megusta.userservice.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Guilherme Camargo
 * */
@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressBuildCommand addressBuildCommand;

    public Address findById(Long id){
        return addressRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Endereço não encontrado."));
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
        return addressRepository.findByUser(user)
                .orElseThrow(() -> new ObjectNotFoundException("Nenhum endereço encontrado."));
    }

    public Address saveAddress(AddressDTO dto){
        Address address = addressBuildCommand.execute(dto);
        if(address.getUser() == null){
            User user = userService.findById(dto.getUserId());
            address.setUser(user);
            user.setAddress(address);
        }
        return save(address);
    }

}
