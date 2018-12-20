package cz.muni.fi.pa165.smartphonEShop.dto;

import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Created by Stefan Holecko
 * Class represents:
 */

@Getter
@Setter
public class PersonChangePersonTypeDTO {

    @NotNull
    private Long id;

    @NotNull
    private PersonType personType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonChangePersonTypeDTO)) return false;
        PersonChangePersonTypeDTO that = (PersonChangePersonTypeDTO) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
