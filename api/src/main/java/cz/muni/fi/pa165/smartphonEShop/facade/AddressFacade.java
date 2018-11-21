package cz.muni.fi.pa165.smartphonEShop.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.AddressDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.enums.AddressEnum;

import java.util.Collection;
import java.util.HashMap;

public interface AddressFacade {
    /**
     * @param id
     * @return Address with specific Id.
     */
    AddressDTO findAddressById(Long id);

    /**
     * @param specificator - HashMap specificator
     *
     *  unique key
     *  AddressEnum             String
     *       |                     |
     *  STREET_NAME      -       Vrbova
     *       .                     .
     *       .                     .
     *       .                     .
     *
     *  You can choose which tags/parameters you want to get
     *  filtered in resulting collection of Addresses
     *
     * @return collection of Addreesses filtered by specificator
     */
    Collection<AddressDTO> findAllAddressesBy(HashMap<AddressEnum,String> specificator);

    /**
     * @return all addreeses
     */
    Collection<AddressDTO> getAllAddresses();

    /**
     * create new address in system.
     * @param address New address.
     * @return primary key(id) of address
     */
    Long createAddress(Address address);

}
