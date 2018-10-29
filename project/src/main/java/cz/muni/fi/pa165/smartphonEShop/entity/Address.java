package cz.muni.fi.pa165.smartphonEShop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Created by Jakub Ondrusek
 * Class represents:
 */
@Entity
@Table(name = "Address") 
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable=false)
    private String streetName;

    @Column(nullable=false)
    private String streetNumber;

    @Column(nullable=false)
    private String city;

    @Column(nullable=false)
    private String country;

    @OneToOne
    @JoinColumn
    private Stock stock;

    @OneToMany(mappedBy = "address")
    @JoinColumn
    private List<Person> people;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(getStreetName(), address.getStreetName()) &&
                Objects.equals(getStreetNumber(), address.getStreetNumber()) &&
                Objects.equals(getCity(), address.getCity()) &&
                Objects.equals(getCountry(), address.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreetName(), getStreetNumber(), getCity(), getCountry());
    }
}
