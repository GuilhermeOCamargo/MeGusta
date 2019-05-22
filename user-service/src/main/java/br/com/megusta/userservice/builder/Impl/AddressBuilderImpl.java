package br.com.megusta.userservice.builder.Impl;

import br.com.megusta.userservice.builder.BaseBuilder;
import br.com.megusta.userservice.builder.Builder;
import br.com.megusta.userservice.model.domain.Address;
import br.com.megusta.userservice.model.domain.User;
import br.com.megusta.userservice.model.dto.AddressDTO;
import org.springframework.stereotype.Component;

/**
 * @author Guilherme Camargo
 * */
@Component
public class AddressBuilderImpl extends BaseBuilder<Address> {

    public AddressBuilderImpl id(Long id){
        entity.setId(id);
        return this;
    }
    public AddressBuilderImpl streetName(String streetName){
        entity.setStreetName(streetName);
        return this;
    }
    public AddressBuilderImpl number(String number){
        entity.setNumber(number);
        return this;
    }
    public AddressBuilderImpl complement(String complement){
        entity.setComplement(complement);
        return this;
    }
    public AddressBuilderImpl zipCode(String zipCode){
        entity.setZipCode(zipCode);
        return this;
    }
    public AddressBuilderImpl user(User user){
        entity.setUser(user);
        return this;
    }
    public AddressBuilderImpl createFromDto(AddressDTO dto){
        id(dto.getId());
        populateBasicValues(dto);
        return this;
    }
    public AddressBuilderImpl updateFromDto(AddressDTO dto){
        populateBasicValues(dto);
        return this;
    }
    private void populateBasicValues(AddressDTO dto){
        streetName(dto.getStreetName());
        number(dto.getNumber());
        complement(dto.getComplement());
        zipCode(dto.getZipCode());
    }

    @Override
    public Builder createEntity() {
        entity = new Address();
        return this;
    }
}
