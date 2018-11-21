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
    Collection<PhoneDTO> findPhonesByStockID (Long stockID);
    
    /**
     * Find all phones.
     * @return Collection of all phones.
     */
    Collection<PhoneDTO> getAllPhones();

    /**
     * creates new phone in system.
     * @param phone New phone.
     */
    Long createPhone(PhoneDTO phone);
}
