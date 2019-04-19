package br.com.megusta.userservice.model.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Guilherme Camargo
 * */
@Entity
public class Address implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 25, nullable = false, unique = true)
    private String streetName;
    @Column(length = 10, nullable = false)
    private String number;
    @Column(length = 25, nullable = true)
    private String complement;;
    @Column(length=8, nullable=false, unique = true)
    private String zipCode;
    @OneToOne
    private User user;

    public Address(Long id, String streetName, String number, String complement, String zipCode) {
        this.id = id;
        this.streetName = streetName;
        this.number = number;
        this.complement = complement;
        this.zipCode = zipCode;
    }
    public Address() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address that = (Address) o;
        return id.equals(that.id) &&
                streetName.equals(that.streetName) &&
                number.equals(that.number) &&
                Objects.equals(complement, that.complement) &&
                zipCode.equals(that.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetName, number, complement, zipCode);
    }

    //Getters And Setters
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
