package cz.muni.fi.pa165.smartphonEShop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Created by Roman Nahalka
 * Class represents: DTO for stock create.
 * @author xnahalka
 */
@Getter
@Setter
public class StockCreateDTO
{
    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    private AddressDTO address;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof StockCreateDTO)) return false;
        StockCreateDTO that = (StockCreateDTO) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getName());
    }

    @Override
    public String toString()
    {
        return "StockCreateDTO{" +
                "name='" + name + '\'' +
                ", addressDTO=" + address +
                '}';
    }
}
