package cz.muni.fi.pa165.smartphonEShop.dto;

import cz.muni.fi.pa165.smartphonEShop.enums.Gender;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(min = 2, max = 50)
    private String firstName;
    
    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;
    
    @NotNull
    @Size(min = 7, max = 100)
    private String email;
    
    @NotNull
    @Size(min = 9, max = 9)
    private String phoneNumber;
    
//    @NotNull
//    private LocalDate dateOfBirth;
    
    @NotNull
    private Gender gender;

//    @NotNull
//    private PersonType personType;
    
   @NotNull
    private AddressCreateDTO address;
    
    @NotNull
    @Size(min = 6)
    private String password;

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
                getGender() == personDTO.getGender();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getFirstName(), getLastName(), getEmail(), getPhoneNumber(), getGender());
    }

    @Override
    public String toString()
    {
        return "PersonCreateDTO{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender=" + gender +
                '}';
    }
}
