package br.com.megusta.userservice.service;

import br.com.megusta.userservice.builder.command.AddressBuildCommand;
import br.com.megusta.userservice.exceptions.DataIntegrityException;
import br.com.megusta.userservice.exceptions.ObjectNotFoundException;
import br.com.megusta.userservice.model.domain.Address;
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
    private AddressBuildCommand addressBuildCommand;

    public Address findById(String id){
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

    public Address saveAddress(AddressDTO dto){
        Address address = addressBuildCommand.execute(dto);
        return save(address);
    }

    public void delete(String id){
        addressRepository.deleteById(id);
    }
}
