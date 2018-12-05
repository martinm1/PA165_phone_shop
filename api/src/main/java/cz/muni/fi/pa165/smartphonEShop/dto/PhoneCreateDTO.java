package cz.muni.fi.pa165.smartphonEShop.dto;

import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Created by Stefan Holecko
 * Class represents:
 */
@Getter
@Setter
public class PhoneCreateDTO {
    @NotNull
    @Size(min = 3, max = 50)
    private String modelName;
    @NotNull
    @Size(min = 1)
    private int price;
    @NotNull
    @Size(min = 3, max = 1500)
    private String technicalInfo;
    @NotNull
    private Manufacturer manufacturer;
    @NotNull
    private StockDTO stock;

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
