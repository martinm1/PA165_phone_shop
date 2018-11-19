package cz.muni.fi.pa165.smartphonEShop.service.service;

<<<<<<< HEAD
import cz.muni.fi.pa165.smartphonEShop.dto.AddressDTO;
=======
>>>>>>> 1a86eeeffcfc2c02300f515c26d9528586bfc7af
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
<<<<<<< HEAD
    List<Address> findAllAdressesBy(HashMap<AddressEnum,String> specificator);
=======
    List<Address> findAllAddressesBy(HashMap<AddressEnum,String> specificator);
>>>>>>> 1a86eeeffcfc2c02300f515c26d9528586bfc7af

    /**
     * @return all addreeses
     */
<<<<<<< HEAD
    List<Address> getAllAdresses();
=======
    List<Address> getAllAddresses();
>>>>>>> 1a86eeeffcfc2c02300f515c26d9528586bfc7af
}
