package cz.muni.fi.pa165.smartphonEShop.entity;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Stefan Holecko
 * Class represents: Stock Entity
 */

@Entity
@Table(name = "Stock") //TODO
@Getter
@Setter
public class Stock {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Column(nullable=false,unique=true)
    private String name;
  
    @OneToMany(mappedBy = "stock")
    private List<Phone> phones = new ArrayList<>();

    @OneToOne
    @NotNull
    private Address address;

    public void addPhone(Phone phone) {
        this.phones.add(phone);
    }
    
    public void removePhone(Phone phone) {
        this.phones.remove(phone);
    }

    public List<Phone> getPhones() {
        return Collections.unmodifiableList(phones);
    }

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
