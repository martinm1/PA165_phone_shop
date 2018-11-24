package cz.muni.fi.pa165.smartphonEShop.entity;

import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn
    private Person person;
    
    @OneToOne
    @JoinColumn
    private Phone phone;
    
    @OneToMany(mappedBy = "order")
    private List<Claim> claims = new ArrayList<>();

    public void addClaim(Claim claim) {
        this.claims.add(claim);
    }
    public List<Claim> getClaims() {
        return Collections.unmodifiableList(claims);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getState() == order.getState() &&
                Objects.equals(getPerson(), order.getPerson()) &&
                Objects.equals(getOrderDate(), order.getOrderDate()) &&
                Objects.equals(getPhone(), order.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getState(), getPerson(), getOrderDate(), getPhone());
    }
}
