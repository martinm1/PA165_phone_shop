package cz.muni.fi.pa165.smartphonEShop.service.service;


import cz.muni.fi.pa165.smartphonEShop.entity.Claim;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimSolution;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimState;

import java.util.Collection;
import java.util.List;


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
    List<Claim> findClaimByOrderId(Long orderId);

    /**
     * Find claims with specific userId
     * @param userId of the  claim
     * @return Collection of claims with given userId.
     */
    List<Claim> findClaimByUserId(Long userId);

    /**
     * Find claims with specific claimState
     * @param claimState of the  claim
     * @return Collection of claims with given claimState.
     */
    List<Claim> findClaimByClaimState(ClaimState claimState);

    /**
     * Find claims with specific claimSolution
     * @param claimSolution of the  claim
     * @return Collection of claims with given claimSolution.
     */
    List<Claim> findClaimByClaimSolution(ClaimSolution claimSolution);

    /**
     * Find all claims.
     * @return Collection of all claims.
     */
    List<Claim> getAllClaims();

    /**
     * Register new claim to system.
     * @param claim New claim.
     */
    Long createClaim(Claim claim);

    void acceptClaim(Claim claim);

    void rejectClaim(Claim claim);
}
