package cz.muni.fi.pa165.smartphonEShop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Stefan Holecko
 * Class represents: Stock Entity
 */

@Entity
@Table(name = "stock") //TODO
@Getter
@Setter

public class Stock {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(nullable=false,unique=true)
    private String name;

    @Column(nullable=false)
    private Long addressId;

    @OneToMany(mappedBy = "stock") //TODO mappedBy?
    private Set<Phone> phones = new HashSet<>();

    public void addPhone(Phone phone) {
        this.phones.add(phone);
    }

    public Set<Phone> getPhones() {
        return Collections.unmodifiableSet(phones);
    }

    @OneToOne(mappedBy = "stock")
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock)) return false;
        Stock stock = (Stock) o;
        return Objects.equals(getName(), stock.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
