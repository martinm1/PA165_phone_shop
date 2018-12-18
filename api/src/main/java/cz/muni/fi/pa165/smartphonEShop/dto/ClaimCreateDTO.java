package cz.muni.fi.pa165.smartphonEShop.dto;

import cz.muni.fi.pa165.smartphonEShop.enums.ClaimSolution;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Created by Roman Nahalka
 * Class represents: DTO for claim create.
 * @author xnahalka
 */
@Getter
@Setter
public class ClaimCreateDTO
{
    @NotNull
    private Long orderId;

    @NotNull
    private ClaimSolution wantedSolutionByCustomer;

    @NotNull
    @Size(min = 10, max = 1000)
    private String reasonOfClaim;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof ClaimCreateDTO)) return false;
        ClaimCreateDTO that = (ClaimCreateDTO) o;
        return Objects.equals(getOrderId(), that.getOrderId()) &&
                getWantedSolutionByCustomer() == that.getWantedSolutionByCustomer();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getOrderId(), getWantedSolutionByCustomer());
    }

    @Override
    public String toString()
    {
        return "ClaimCreateDTO{" +
                "order=" + orderId +
                ", wantedSolutionByCustomer=" + wantedSolutionByCustomer +
                ", reasonOfClaim='" + reasonOfClaim + '\'' +
                '}';
    }
}
