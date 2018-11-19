package cz.muni.fi.pa165.smartphonEShop.dto;

import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author martin
 */
@Getter
@Setter
public class PhoneDTO {
    
    private Long id;
    private String modelName;
    private int price;
    private String technicalInfo;
    private Manufacturer manufacturer;
    private OrderDTO order;
    //private StockDTO stock;
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneDTO)) return false;
        PhoneDTO phoneDTO = (PhoneDTO) o;
        return Objects.equals(getModelName(), phoneDTO.getModelName()) &&
                Objects.equals(getTechnicalInfo(), phoneDTO.getTechnicalInfo()) &&
                getManufacturer() == phoneDTO.getManufacturer();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModelName(), getTechnicalInfo(), getManufacturer());
    }
}
