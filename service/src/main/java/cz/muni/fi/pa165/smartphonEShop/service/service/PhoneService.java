package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;

import java.util.List;

/**
 * Created by Roman Nahalka
 * Class represents: PhoneService interface.
 */

public interface PhoneService
{
    /**
     * Add new phone to database.
     * @param phone to be created.
     * @return id of created phone.
     */
    Long createPhone(Phone phone);

    /**
     * Find phone with specific id.
     * @param id primary key for phone.
     * @return Phone with given id.
     */
    Phone findPhoneById(Long id);

    /**
     * Find phones with specific model name.
     * @param modelName of phone.
     * @return List of phones with given model name.
     */
    List<Phone> findPhonesByModelName(String modelName);

    /**
     * Find phones with specific price.
     * @param price of phone.
     * @return List of phones with given price.
     */
    List<Phone> findPhonesByPrice(int price);

    /**
     * Find phones with specific technical information.
     * @param technicalInfo of phones.
     * @return List of phones with given technical infromation.
     */
    List<Phone> findPhonesByTechnicalInfo(String technicalInfo);

    /**
     * Find phones with specific manufacturer.
     * @param manufacturer of phones.
     * @return List of phones with given manufacturer.
     */
    List<Phone> findPhonesByManufacture(Manufacturer manufacturer);

    /**
     * Find phones with specific stock.
     * @param stockId primary key for stock.
     * @return List of phones with given stock.
     */
    List<Phone> findPhonesByStock(Long stockId);

    /**
     * Find all phones.
     * @return List of all phones.
     */
    List<Phone> findAllPhones();
}
