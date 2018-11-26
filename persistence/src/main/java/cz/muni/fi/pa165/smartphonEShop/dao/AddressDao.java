package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.enums.AddressEnum;

import cz.muni.fi.pa165.smartphonEShop.exceptions.DAOException;

import java.util.HashMap;
import java.util.List;


/**
 * Created by Roman Nahalka
 * Interface represents: Manager for AddressDaoImpl
 * @author xnahalka
 */
public interface AddressDao
{
    /**
     * Create new address in database.
     * @param address to be created.
     * @throws DAOException when address is null.
     */
    void create(Address address);

    /**
     * Update address in database.
     * @param address to be created.
     * @throws DAOException when address is null.
     */
    void update(Address address);

    /**
     * Delete address in database.
     * @param address to be deleted.
     * @throws DAOException when address is null.
     */
    void delete(Address address);

    /**
     * Find address with specific id in database.
     * @param id primary key for address.
     * @return address with given id, null if no such exists.
     * @throws DAOException when id is null or less then 0.
     */
    Address findById(Long id);



    /**
     * @return list of all addresses in database.
     */
    List<Address> findAll();


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
    List<Address> findAllAddressesBy(HashMap<AddressEnum, String> specificator);
}
