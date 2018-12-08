package cz.muni.fi.pa165.smartphonEShop.dto;

import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by Stefan Holecko
 * Class represents:
 */
@Getter
@Setter
public class OrderCreateDTO {

    @NotNull
    private OrderState state;
    @NotNull
    private LocalDate orderDate;
    @NotNull
    private PersonDTO person;
    @NotNull
    private PhoneDTO phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderCreateDTO)) return false;
        OrderCreateDTO that = (OrderCreateDTO) o;
        return getState() == that.getState() &&
                Objects.equals(getOrderDate(), that.getOrderDate()) &&
                Objects.equals(getPerson(), that.getPerson()) &&
                Objects.equals(getPhone(), that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getState(), getOrderDate(), getPerson(), getPhone());
    }

    @Override
    public String toString() {
        return "OrderCreateDTO{" +
                "state=" + state +
                ", orderDate=" + orderDate +
                ", person=" + person +
                ", phone=" + phone +
                '}';
    }
}
