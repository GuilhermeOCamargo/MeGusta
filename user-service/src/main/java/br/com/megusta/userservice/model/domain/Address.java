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

    public Address(String streetName, String number, String complement, String zipCode, String name) {
        this.streetName = streetName;
        this.number = number;
        this.complement = complement;
        this.zipCode = zipCode;
        this.name = name;
    }
    public Address() {}

    //Getters And Setters
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getComplement() { return complement;}
    public void setComplement(String complement) {
        this.complement = complement;
    }
    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
}
