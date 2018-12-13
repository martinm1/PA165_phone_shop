package cz.muni.fi.pa165.smartphonEShop.dto;

import cz.muni.fi.pa165.smartphonEShop.enums.Gender;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author martin
 */
@Getter
@Setter
public class PersonCreateDTO {
    @NotNull
    private String firstName;
    
    @NotNull
    private String lastName;
    
    @NotNull
    private String email;
    
    @NotNull
    private String phoneNumber;
    
    @NotNull
    private LocalDate dateOfBirth;
    
    @NotNull
    private Gender gender;
    
    @NotNull
    private PersonType personType;
    
    @NotNull
    private AddressDTO address;
    
    @NotNull
    private String passwordHash;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof PersonDTO)) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(getFirstName(), personDTO.getFirstName()) &&
                Objects.equals(getLastName(), personDTO.getLastName()) &&
                Objects.equals(getEmail(), personDTO.getEmail()) &&
                Objects.equals(getPhoneNumber(), personDTO.getPhoneNumber()) &&
                Objects.equals(getDateOfBirth(), personDTO.getDateOfBirth()) &&
                getGender() == personDTO.getGender() &&
                getPersonType() == personDTO.getPersonType() &&
                Objects.equals(getAddress(), personDTO.getAddress());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getFirstName(), getLastName(), getEmail(), getPhoneNumber(), getDateOfBirth(), getGender(), getPersonType(), getAddress());
    }

    @Override
    public String toString()
    {
        return "PersonCreateDTO{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", personType=" + personType +
                ", address=" + address +
                '}';
    }
}
