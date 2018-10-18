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

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getOrderId()
    {
        return orderId;
    }

    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public ClaimSolution getWantedSolutionByCustomer()
    {
        return wantedSolutionByCustomer;
    }

    public void setWantedSolutionByCustomer(ClaimSolution wantedSolutionByCustomer)
    {
        this.wantedSolutionByCustomer = wantedSolutionByCustomer;
    }

    public String getTechnicalReport()
    {
        return technicalReport;
    }

    public void setTechnicalReport(String technicalReport)
    {
        this.technicalReport = technicalReport;
    }

    public String getReasonOfClaim()
    {
        return reasonOfClaim;
    }

    public void setReasonOfClaim(String reasonOfClaim)
    {
        this.reasonOfClaim = reasonOfClaim;
    }

    public ClaimState getClaimState()
    {
        return claimState;
    }

    public void setClaimState(ClaimState claimState)
    {
        this.claimState = claimState;
    }

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
