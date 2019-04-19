package br.com.megusta.userservice.model.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author Guilherme Camargo
 * */
public class AddressDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotNull(message = "Insira o número do logradouro.")
    private String number;
    private String complement;
    @NotNull(message = "Insira o nome da rua.")
    private String streetName;
    @NotNull(message = "Insira do CEP.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Cep inválido.")
    private String zipCode;

    public AddressDTO(Long id, String number, String complement, String streetName, String zipCode) {
        this.id = id;
        this.number = number;
        this.complement = complement;
        this.streetName = streetName;
        this.zipCode = zipCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

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

}
