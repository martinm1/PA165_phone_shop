package cz.muni.fi.pa165.smartphonEShop.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.PhoneDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import java.util.Collection;

/**
 *
 * @author martin
 */
public interface PhoneFacade {
    /**
     * Find phone with specific id
     * @param id primary key for phone
     * @return Phone with given id.
     */
    PhoneDTO findPhoneById(Long id);

    /**
     * Find phones with specific model name.
     * @param modelName of phone.
     * @return Collection of phones with given model name.
     */
    Collection<PhoneDTO> findPhonesByModelName(String modelName);

    /**
     * Find phones with specific price.
     * @param price of phone.
     * @return Collection of phones with given price.
     */
    Collection<PhoneDTO> findPhonesByPrice (int price);

    /**
     * Find phones with specific technical info.
     * @param technicalInfo of phone.
     * @return Collection of phones with given technical info.
     */
    Collection<PhoneDTO> findPhonesByTechnicalInfo (String technicalInfo);
    
    /**
     * Find phones with specific manufacturer.
     * @param manufacturer of phone.
     * @return Collection of phones with given manufacturer.
     */
    Collection<PhoneDTO> findPhonesByManufacturer (Manufacturer manufacturer);
    
    /**
     * Find phones with specific stock.
     * @param stockID of phone.
     * @return Collection of phones with given stock.
     */
    Collection<PhoneDTO> findPhonesByManufacturer (Long stockID);
    
    /**
     * Find all phones.
     * @return Collection of all phones.
     */
    Collection<PhoneDTO> getAllPhones();


    /**
     * Add specific order to specific phone.
     * @param phoneId primary key for phone.
     * @param orderId primary key for order.
     */
    void addOrder(Long phoneId, Long orderId);
    
    /**
     * Add specific stock to specific phone.
     * @param phoneId primary key for phone.
     * @param stockId primary key for stock.
     */
    void addStock(Long phoneId, Long stockId);

    /**
     * Register new phone to system.
     * @param phone New phone.
     */
    void registerPhone(PhoneDTO phone);
}
