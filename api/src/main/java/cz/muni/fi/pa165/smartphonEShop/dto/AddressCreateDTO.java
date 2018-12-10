package cz.muni.fi.pa165.smartphonEShop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
public class AddressCreateDTO {
    @NotNull
    @Size(min = 2, max = 50)
    private String streetName;
    @NotNull
    @Size(min = 1, max = 10)
    private String streetNumber;
    @NotNull
    @Size(min = 3, max = 100)
    private String city;
    @NotNull
    @Size(min = 3, max = 80)
    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressCreateDTO)) return false;
        AddressCreateDTO that = (AddressCreateDTO) o;
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
        return "AddressCreateDTO{" +
                "streetName='" + streetName + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
