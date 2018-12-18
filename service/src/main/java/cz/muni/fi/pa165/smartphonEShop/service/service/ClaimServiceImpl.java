package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.dao.ClaimDao;
import cz.muni.fi.pa165.smartphonEShop.entity.Claim;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimSolution;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimState;
import cz.muni.fi.pa165.smartphonEShop.exceptions.EshopServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Stefan Holecko
 * Class represents: Implementation of ClaimService interface
 */

@Service
public class ClaimServiceImpl implements ClaimService {


    @Autowired
    private ClaimDao claimDao;

    @Override
    public Claim findClaimById(Long id) {
        return claimDao.findById(id);
    }

    @Override
    public List<Claim> findClaimByOrderId(Long orderId) {
        return claimDao.findClaimByOrderId(orderId);
    }

    @Override
    public List<Claim> findClaimByUserId(Long userId) {
        return claimDao.findClaimByUserId(userId);
    }

    @Override
    public List<Claim> findClaimByClaimState(ClaimState claimState) {
        return claimDao.findClaimByClaimState(claimState);
    }

    @Override
    public List<Claim> findClaimByClaimSolution(ClaimSolution claimSolution) {
        return claimDao.findClaimByClaimSolution(claimSolution);
    }

    @Override
    public List<Claim> getAllClaims() {
        return claimDao.findAll();
    }

    @Override
    public Long createClaim(Claim claim) {
        claimDao.create(claim);
        return claim.getId();
    }

    @Override
    public void acceptClaim(Claim claim)
    {
        isInCreatedState(claim);
        claim.setClaimState(ClaimState.ACCEPTED);

        claimDao.update(claim);
    }

    @Override
    public void rejectClaim(Claim claim)
    {
        isInCreatedState(claim);
        claim.setClaimState(ClaimState.REJECTED);

        claimDao.update(claim);
    }

    private void isInCreatedState(Claim claim)
    {
        if(claim.getClaimState() != ClaimState.CREATED)
            throw new EshopServiceException("Cannot change state from another state then created!");
    }

    @Override
    public void addReport(Claim claim, String report) {
        claim.setTechnicalReport(report);

        claimDao.update(claim);
    }
}
