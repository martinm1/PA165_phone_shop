package cz.muni.fi.pa165.smartphonEShop.entity;

import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import java.util.Objects;

/**
 * Created by Jakub Ondrusek
 * Class represents:
 */

@Entity
@Table(name="\"Order\"")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private OrderState state;

    @Column(nullable=false)
    private Long personId;

    @Column(nullable=false)
    private LocalDate orderDate;

    @Column(nullable=false)
    private Long phoneId;
    
    @ManyToOne
    @JoinColumn
    private Person person;
    
    @OneToOne
    @JoinColumn
    private Phone phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getState() == order.getState() &&
                Objects.equals(getPersonId(), order.getPersonId()) &&
                Objects.equals(getOrderDate(), order.getOrderDate()) &&
                Objects.equals(getPhoneId(), order.getPhoneId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getState(), getPersonId(), getOrderDate(), getPhoneId());
    }
}
