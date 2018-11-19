package cz.muni.fi.pa165.smartphonEShop.dto;


import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import java.time.LocalDate;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author martin
 */
@Getter
@Setter
public class OrderDTO {
    
    private Long id;
    private OrderState state;
    private LocalDate orderDate;
    private PersonDTO person;
    private PhoneDTO phone;
    
    //private List<ClaimDTO> claims;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO)) return false;
        OrderDTO order = (OrderDTO) o;
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
