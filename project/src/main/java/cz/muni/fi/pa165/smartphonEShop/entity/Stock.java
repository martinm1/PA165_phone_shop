package cz.muni.fi.pa165.smartphonEShop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Created by Stefan Holecko
 * Class represents: Stock Entity
 */

@Entity
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
