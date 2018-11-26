package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.enums.AddressEnum;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author martin
 */
public interface AddressService {
     /**
     * Find address with specific ID.
     * @param od of address.
     * @return Address with given id.
     */
    Address findAddressById(Long od);
    
    /**
     * Find addresses with specific parameters.
     * @param specificator
     * @return Stock with given phoneId.
     */
    List<Address>  findAllAddressesBy(HashMap<AddressEnum, String> specificator);

    /**
     * Find all addresses.
     * @return List of all addresses.
     */
    List<Address> getAllAddresses();


    /**
     * create new address in system.
     * @param address New address.
     * @return primary key(id) of address
     */
    Long createAddress(Address address);

}
