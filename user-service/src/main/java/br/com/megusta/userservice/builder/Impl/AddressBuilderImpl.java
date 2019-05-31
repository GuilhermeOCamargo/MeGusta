package br.com.megusta.userservice.builder.Impl;

import br.com.megusta.userservice.builder.BaseBuilder;
import br.com.megusta.userservice.builder.Builder;
import br.com.megusta.userservice.model.domain.Address;
import org.springframework.stereotype.Component;

/**
 * @author Guilherme Camargo
 * */
@Component
public class AddressBuilderImpl extends BaseBuilder<Address> {

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
    public AddressBuilderImpl name(String name){
        entity.setName(name);
        return this;
    }

    @Override
    public Builder createEntity() {
        entity = new Address();
        return this;
    }
}
