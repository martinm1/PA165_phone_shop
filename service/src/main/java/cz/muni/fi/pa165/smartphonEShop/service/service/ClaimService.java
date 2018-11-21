package cz.muni.fi.pa165.smartphonEShop.service.service;


import cz.muni.fi.pa165.smartphonEShop.entity.Claim;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimSolution;
import java.util.Collection;


/**
 *
 * @author martin
 */
public interface ClaimService {

    /**
     * Find claim with specific id
     * @param id primary key for claim
     * @return claim with given id.
     */
    Claim findClaimById(Long id);

    /**
     * Find claims with specific orderId
     * @param orderId of the  claim
     * @return Collection of claims with given orderId.
     */
    Collection<Claim> findClaimByOrderId(Long orderId);

    /**
     * Find claims with specific userId
     * @param userId of the  claim
     * @return Collection of claims with given userId.
     */
    Collection<Claim> findClaimByUserId(Long userId);

    /**
     * Find claims with specific claimState
     * @param claimState of the  claim
     * @return Collection of claims with given claimState.
     */
    Collection<Claim> findClaimByClaimState(ClaimState claimState);

    /**
     * Find claims with specific claimSolution
     * @param claimSolution of the  claim
     * @return Collection of claims with given claimSolution.
     */
    Collection<Claim> findClaimByClaimSolution(ClaimSolution claimSolution);

    /**
     * Find all claims.
     * @return Collection of all claims.
     */
    Collection<Claim> getAllClaims();

    /**
     * Register new claim to system.
     * @param claim New claim.
     */
    Long createClaim(Claim claim);
}
