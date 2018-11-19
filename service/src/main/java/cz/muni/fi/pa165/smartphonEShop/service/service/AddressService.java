package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.enums.AddressEnum;

import java.util.HashMap;
import java.util.List;

public interface AddressService {
    /**
     * @param id
     * @return Address with specific Id.
     */
    Address findAddressById(Long id);

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
    List<Address> findAllAddressesBy(HashMap<AddressEnum,String> specificator);

    /**
     * @return all addreeses
     */
    List<Address> getAllAddresses();
}
