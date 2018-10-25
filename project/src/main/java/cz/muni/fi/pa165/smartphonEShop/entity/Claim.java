package cz.muni.fi.pa165.smartphonEShop.entity;

import cz.muni.fi.pa165.smartphonEShop.enums.ClaimSolution;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimState;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Roman Nahalka
 * Class represents:
 */

@Entity
@Getter
@Setter
public class Claim
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private ClaimSolution wantedSolutionByCustomer;

    private String technicalReport;

    @Column(nullable = false)
    private String reasonOfClaim;

    @Column(nullable = false)
    private ClaimState claimState;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Claim)) return false;
        Claim claim = (Claim) o;
        return Objects.equals(getOrderId(), claim.getOrderId()) &&
                getWantedSolutionByCustomer() == claim.getWantedSolutionByCustomer();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getOrderId(), getWantedSolutionByCustomer());
    }
}
