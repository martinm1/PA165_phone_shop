package cz.muni.fi.pa165.smartphonEShop.dto;

import cz.muni.fi.pa165.smartphonEShop.entity.Person;
import cz.muni.fi.pa165.smartphonEShop.entity.Stock;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class AddressDTO {

    private Long id;
    private String streetName;
    private String streetNumber;
    private String city;
    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressDTO)) return false;
        AddressDTO that = (AddressDTO) o;
        return Objects.equals(getStreetName(), that.getStreetName()) &&
                Objects.equals(getStreetNumber(), that.getStreetNumber()) &&
                Objects.equals(getCity(), that.getCity()) &&
                Objects.equals(getCountry(), that.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreetName(), getStreetNumber(), getCity(), getCountry());
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "id=" + id +
                ", streetName='" + streetName + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}