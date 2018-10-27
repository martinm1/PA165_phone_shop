package cz.muni.fi.pa165.smartphonEShop.entity;

import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Stefan Holecko
 * Class represents: Phone Entity
 */

@Entity
@Table(name = "phone") //TODO
@Getter
@Setter
public class Phone {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable=false)
    private String modelName;

    @Column(nullable=false)
    private int price;

    @Column(nullable=false)
    private String technicalInfo;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private Manufacturer manufacturer;

    @Column(nullable=false)
    private Long stockId;

    @OneToOne(mappedBy = "order")
    private Order order;

    @ManyToOne
    @Column(nullable=false)
    private Stock stock;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;
        Phone phone = (Phone) o;
        return Objects.equals(getModelName(), phone.getModelName()) &&
                Objects.equals(getTechnicalInfo(), phone.getTechnicalInfo()) &&
                getManufacturer() == phone.getManufacturer();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModelName(), getTechnicalInfo(), getManufacturer());
    }

}
