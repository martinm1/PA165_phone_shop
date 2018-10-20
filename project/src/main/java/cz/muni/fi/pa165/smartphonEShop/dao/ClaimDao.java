/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Claim;
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
     * @throws IllegalArgumentException when claim is null.
     */
    public void create(Claim claim);


    /**
     * Update claim in database
     * @param claim to be updated
     * @throws IllegalArgumentException when claim is null.
     */
    public void update(Claim claim);


    /**
     * Delete claim from database
     * @param claim to be removed
     * @throws IllegalArgumentException when claim is null.
     */
    public void delete(Claim claim);


    /**
     * Find claim with specified ID in database.
     * @param id primary key of requested claim
     * @return claim with given id, null if no such exists.
     * @throws IllegalArgumentException when id is null or less than 0.
     */
    public Claim findById(Long id);


    /**
     * @return List of all claims from database
     */
    public List<Claim> findAll();
}