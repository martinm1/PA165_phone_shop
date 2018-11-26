package cz.muni.fi.pa165.smartphonEShop.dto;

import cz.muni.fi.pa165.smartphonEShop.enums.Gender;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Created by Roman Nahalka
 * Class represents: Data transfer object of Person.
 * @author xnahalka
 */

@Getter
@Setter
public class PersonDTO
{
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Gender gender;
    private PersonType personType;
    private AddressDTO address;
    private List<OrderDTO> orders;
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
        return "PersonDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", personType=" + personType +
                ", address=" + address +
                ", orders=" + orders +
                '}';
    }
}
