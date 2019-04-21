package br.com.megusta.userservice.builder;

import br.com.megusta.userservice.model.domain.Address;
import br.com.megusta.userservice.model.domain.User;
import br.com.megusta.userservice.model.dto.AddressDTO;

/**
 * @author Guilherme Camargo
 * */
public class AddressBuilder {
    private Address address;

    public AddressBuilder(){
        this.address = new Address();
    }
    public AddressBuilder(Address address){
        this.address = address;
    }
    private AddressBuilder id(Long id){
        this.address.setId(id);
        return this;
    }
    private AddressBuilder streetName(String streetName){
        this.address.setStreetName(streetName);
        return this;
    }
    private AddressBuilder number(String number){
        this.address.setNumber(number);
        return this;
    }
    private AddressBuilder complement(String complement){
        this.address.setComplement(complement);
        return this;
    }
    private AddressBuilder zipCode(String zipCode){
        this.address.setZipCode(zipCode);
        return this;
    }
    public AddressBuilder user(User user){
        this.address.setUser(user);
        return this;
    }
    public AddressBuilder createFromDto(AddressDTO dto){
        id(dto.getId());
        populateBasicValues(dto);
        return this;
    }
    public AddressBuilder updateFromDto(AddressDTO dto){
        populateBasicValues(dto);
        return this;
    }
    private void populateBasicValues(AddressDTO dto){
        streetName(dto.getStreetName());
        number(dto.getNumber());
        complement(dto.getComplement());
        zipCode(dto.getZipCode());
    }
    public Address build(){
        return this.address;
    }
}
