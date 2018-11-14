package cz.muni.fi.pa165.smartphonEShop.dto;

import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by Stefan Holecko
 * Class represents:
 */
@Getter
@Setter
public class StockDTO {

    private Long id;
    private String name;
    private List<Phone> phones = new ArrayList<>();
    public void addPhone(Phone phone) {
        this.phones.add(phone);
    }
    public List<Phone> getPhones() {
        return Collections.unmodifiableList(phones);
    }
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockDTO)) return false;
        StockDTO stockDTO = (StockDTO) o;
        return Objects.equals(getName(), stockDTO.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "StockDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phones=" + phones +
                ", address=" + address +
                '}';
    }
}
