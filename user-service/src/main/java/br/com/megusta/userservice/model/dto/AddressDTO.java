package br.com.megusta.userservice.model.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author Guilherme Camargo
 * */
public class AddressDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private String id;
    @NotNull(message = "Insira o nome do endereço.")
    private String name;
    @NotNull(message = "Insira o número do logradouro.")
    private String number;
    private String complement;
    @NotNull(message = "Insira o nome da rua.")
    private String streetName;
    @NotNull(message = "Insira do CEP.")
    @Pattern(regexp = "\\d{5}\\d{3}", message = "Cep inválido.")
    private String zipCode;
    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

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
}
