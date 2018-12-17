/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.smartphonEShop.entity;



import cz.muni.fi.pa165.smartphonEShop.enums.Gender;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author martin
 * Class represents: Person Entity
 */
@Entity
@Table(name = "Person") 
@Getter
@Setter
public class Person {
    
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    private String passwordHash;

    @Column(nullable=false)
    private String firstName;
    
    @Column(nullable=false)
    private String lastName;
    
    @ManyToOne
    @JoinColumn
    private Address address;
    
    @Column(nullable=false, unique = true)
    private String email;
    
    @Column(nullable=false, unique = true)
    private String phoneNumber;
    
    @Column
    private LocalDate dateOfBirth;
    
    @Column(nullable=false)
    private Gender gender;
    
    @Column(nullable=false)
    private PersonType personType;
    
    @OneToMany(mappedBy = "person")
    private List<Order> orders = new ArrayList<>();
    
    public void addOrder(Order order) {
        this.orders.add(order);
    }
    
    public void removeOrder(Order order) {
        this.orders.remove(order);
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getFirstName(), person.getFirstName()) &&
                Objects.equals(getLastName(), person.getLastName()) &&
                Objects.equals(getAddress(), person.getAddress()) &&
                Objects.equals(getEmail(), person.getEmail()) &&
                Objects.equals(getPhoneNumber(), person.getPhoneNumber()) &&
                Objects.equals(getDateOfBirth(), person.getDateOfBirth()) &&
                getGender() == person.getGender() &&
                getPersonType() == person.getPersonType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPhoneNumber());
    }
}
