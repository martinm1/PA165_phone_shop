package cz.muni.fi.pa165.smartphonEShop.entity;

import cz.muni.fi.pa165.smartphonEShop.enums.ClaimSolution;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Created by Roman Nahalka
 */

@Entity
@Getter
@Setter
public class Claim
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    private Order order;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ClaimSolution wantedSolutionByCustomer;

    private String technicalReport;

    @Column(nullable = false)
    @NotNull
    private String reasonOfClaim;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ClaimState claimState;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Claim)) return false;
        Claim claim = (Claim) o;
        return Objects.equals(getOrder(), claim.getOrder()) &&
                getWantedSolutionByCustomer() == claim.getWantedSolutionByCustomer();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getOrder(), getWantedSolutionByCustomer());
    }
}
