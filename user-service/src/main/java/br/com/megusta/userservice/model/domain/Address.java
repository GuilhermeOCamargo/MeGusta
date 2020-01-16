package br.com.megusta.userservice.model.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Guilherme Camargo
 * */
@Document(collection = "address")
public class Address implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private String streetName;
    private String number;
    private String complement;
    private String zipCode;

    private Address(String streetName, String number, String complement, String zipCode, String name) {
        this.streetName = streetName;
        this.number = number;
        this.complement = complement;
        this.zipCode = zipCode;
        this.name = name;
    }
    @Deprecated
    public Address() {}

    public String getNumber() {
        return number;
    }
    public String getComplement() { return complement;}
    public String getStreetName() {
        return streetName;
    }
    public String getZipCode() {
        return zipCode;
    }
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static AddressBuilder builder(){
        return new AddressBuilder();
    }
    public static class AddressBuilder{

        private String streetName;
        private String number;
        private String complement;
        private String zipCode;
        private String name;

        public AddressBuilder withStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public AddressBuilder withNumber(String number) {
            this.number = number;
            return this;
        }

        public AddressBuilder withComplement(String complement) {
            this.complement = complement;
            return this;
        }

        public AddressBuilder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public AddressBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public Address build() {
            return new Address(streetName, number, complement, zipCode, name);
        }
    }
}
