package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Phone;

import java.util.List;

/**
 * @author Jakub Ondrusek
 * Interface represents: Manager for PhoneDaoImpl
 */
public interface PhoneDao {

    /**
     * Create new phone in database
     * @param phone to be created.
     * @throws IllegalArgumentException when phone is null.
     */
    public void create(Phone phone);


    /**
     * Update phone in database
     * @param phone to be updated
     * @throws IllegalArgumentException when phone is null.
     */
    public void update(Phone phone);


    /**
     * Delete phone from database
     * @param phone to be removed
     * @throws IllegalArgumentException when phone is null.
     */
    public void delete(Phone phone);


    /**
     * Find phone with specified ID in database.
     * @param id primary key of requested phone.
     * @return phone with given id, null if no such exists.
     * @throws IllegalArgumentException when id is null or less than 0.
     */
    public Phone findById(Long id);


    /**
     * @return List of all phones from database
     */
    public List<Phone> findAll();
}
