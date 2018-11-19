package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;

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

    /**
     * Find phones with specific model name.
     * @param modelName of phone.
     * @return List of phones with given model name.
     */
    public List<Phone> findPhonesByModelName(String modelName);

    /**
     * Find phones with specific price.
     * @param price of phone.
     * @return List of phones with given price.
     */
    public List<Phone> findPhonesByPrice(int price);

    /**
     * Find phones with specific technical information.
     * @param technicalInfo of phones.
     * @return List of phones with given technical infromation.
     */
    public List<Phone> findPhonesByTechnicalInfo(String technicalInfo);

    /**
     * Find phones with specific manufacturer.
     * @param manufacturer of phones.
     * @return List of phones with given manufacturer.
     */
    public List<Phone> findPhonesByManufacturer(Manufacturer manufacturer);

    /**
     * Find phones with specific stock.
     * @param stockId primary key for stock.
     * @return List of phones with given stock.
     */
    public List<Phone> findPhonesByStock(Long stockId);
}
