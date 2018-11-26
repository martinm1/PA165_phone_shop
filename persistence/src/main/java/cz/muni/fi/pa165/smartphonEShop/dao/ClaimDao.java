/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Claim;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimSolution;
import cz.muni.fi.pa165.smartphonEShop.enums.ClaimState;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author martin
 * Interface represents: Manager for ClaimDaoImpl
 */
public interface ClaimDao {
    /**
     * Create new claim in database
     * @param claim to be created
     * @throws DAOException when claim is null.
     */
    void create(Claim claim);


    /**
     * Update claim in database
     * @param claim to be updated
     * @throws DAOException when claim is null.
     */
    void update(Claim claim);


    /**
     * Delete claim from database
     * @param claim to be removed
     * @throws DAOException when claim is null.
     */
    void delete(Claim claim);


    /**
     * Find claim with specified ID in database.
     * @param id primary key of requested claim
     * @return claim with given id, null if no such exists.
     * @throws DAOException when id is null or less than 0.
     */
    Claim findById(Long id);


    /**
     * @return List of all claims from database
     */
    List<Claim> findAll();


    /**
     * Find claims with specific orderId
     * @param orderId of the  claim
     * @return Collection of claims with given orderId.
     * @throws DAOException when orderId is null or less than 0.
     */
    List<Claim> findClaimByOrderId(Long orderId);

    /**
     * Find claims with specific userId
     * @param userId of the  claim
     * @return Collection of claims with given userId.
     * @throws DAOException when userId is null or less than 0.
     */
    List<Claim> findClaimByUserId(Long userId);

    /**
     * Find claims with specific claimState
     * @param claimState of the  claim
     * @return Collection of claims with given claimState.
     * @throws DAOException when claimState is null.
     */
    List<Claim> findClaimByClaimState(ClaimState claimState);

    /**
     * Find claims with specific claimSolution
     * @param claimSolution of the  claim
     * @return Collection of claims with given claimSolution.
     * @throws DAOException when claimSolution is null.
     */
    List<Claim> findClaimByClaimSolution(ClaimSolution claimSolution);
}