package cz.muni.fi.pa165.smartphonEShop.dto;

import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimSolution;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimState;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Created by Stefan Holecko
 * Class represents:
 */
@Getter
@Setter
public class ClaimDTO {

    private Long id;
    private Order order;
    private ClaimSolution wantedSolutionByCustomer;
    private String technicalReport;
    private String reasonOfClaim;
    private ClaimState claimState;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClaimDTO)) return false;
        ClaimDTO claimDTO = (ClaimDTO) o;
        return Objects.equals(getOrder(), claimDTO.getOrder()) &&
                getWantedSolutionByCustomer() == claimDTO.getWantedSolutionByCustomer();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrder(), getWantedSolutionByCustomer());
    }

    @Override
    public String toString() {
        return "ClaimDTO{" +
                "id=" + id +
                ", order=" + order +
                ", wantedSolutionByCustomer=" + wantedSolutionByCustomer +
                ", technicalReport='" + technicalReport + '\'' +
                ", reasonOfClaim='" + reasonOfClaim + '\'' +
                ", claimState=" + claimState +
                '}';
    }
}
