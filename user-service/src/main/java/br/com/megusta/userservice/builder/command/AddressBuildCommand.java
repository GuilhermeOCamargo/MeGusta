package br.com.megusta.userservice.builder.command;

import br.com.megusta.userservice.builder.Impl.AddressBuilderImpl;
import br.com.megusta.userservice.model.domain.Address;
import br.com.megusta.userservice.model.dto.AddressDTO;
import br.com.megusta.userservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Guilherme Camargo
 * */
@Component
public class AddressBuildCommand {
    @Autowired
    private AddressBuilderImpl addressBuilder;
    @Autowired
    private AddressService addressService;

    private Address executeCreationBuild(AddressDTO dto){
        addressBuilder.createEntity();
        return addressBuilder.streetName(dto.getStreetName()).number(dto.getNumber())
                .complement(dto.getComplement()).zipCode(dto.getZipCode()).name(dto.getName()).build();
    }
    private Address executeUpdateBuild(AddressDTO dto){
        addressBuilder.createEntity(addressService.findById(dto.getId()));
        return addressBuilder.streetName(dto.getStreetName()).number(dto.getNumber())
                .complement(dto.getComplement()).zipCode(dto.getZipCode()).name(dto.getName()).build();
    }
    public Address execute(AddressDTO dto){
        if(dto.getId() == null)
            return executeCreationBuild(dto);
        else
            return executeUpdateBuild(dto);
    }
}
