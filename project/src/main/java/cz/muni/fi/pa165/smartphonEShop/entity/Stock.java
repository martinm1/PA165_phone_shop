package cz.muni.fi.pa165.smartphonEShop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by Stefan Holecko
 * Class represents: Stock Entity
 */

@Entity
@Getter
@Setter
@Table(name = "Stock")
public class Stock {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(nullable=false,unique=true)
    private String name;

    @OneToMany(mappedBy = "Stock")
    private List<Phone> phones = new ArrayList<>();

    public void addPhone(Phone phone) {
        this.phones.add(phone);
    }

    public List<Phone> getPhones() {
        return Collections.unmodifiableList(phones);
    }

    @OneToOne(mappedBy = "Stock")
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
