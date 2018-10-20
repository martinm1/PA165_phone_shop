/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.smartphonEShop.entity;



import cz.muni.fi.pa165.smartphonEShop.enums.Gender;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author martin
 * Class represents: Person Entity
 */
@Entity
@Getter
@Setter
public class Person {
    
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable=false)
    private String firstName;
    
    @Column(nullable=false)
    private String lastName;

    @Column(nullable=false)
    private Long addressId;
    
    @Column(nullable=false)
    private String email;
    
    @Column(nullable=false)
    private String phoneNumber;
    
    @Column(nullable=false)
    private LocalDate dateOfBirth;
    
    @Column(nullable=false)
    private Gender gender;
    
    @Column(nullable=false)
    private PersonType personType;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (! (obj instanceof Person))
            return false;
        Person other = (Person) obj;
        return (    this.getFirstName().equals(other.getFirstName())
                &&   this.getLastName().equals(other.getLastName())
                &&  this.getAddressId().equals(other.getAddressId())
                &&      this.getEmail().equals(other.getEmail())
                &&this.getPhoneNumber().equals(other.getPhoneNumber())
                &&this.getDateOfBirth().equals(other.getDateOfBirth())
                &&     this.getGender().equals(other.getGender())
                && this.getPersonType().equals(other.getPersonType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        return result;
    }
}
