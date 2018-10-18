package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Address;

import java.util.List;

/**
 * Created by Roman Nahalka
 * Interface represents: Manager for AddressDaoImpl
 */
public interface AddressDao
{
    /**
     * Create new address in database.
     * @param address to be created.
     * @throws IllegalArgumentException when address is null.
     */
    void create(Address address);

    /**
     * Update address in database.
     * @param address to be created.
     * @throws IllegalArgumentException when address is null.
     */
    void update(Address address);

    /**
     * Delete address in database.
     * @param address to be deleted.
     * @throws IllegalArgumentException when address is null.
     */
    void delete(Address address);

    /**
     * Find address with specific id in database.
     * @param id primary key for address.
     * @return address with given id, null if no such exists.
     * @throws IllegalArgumentException when id is null or less then 0.
     */
    Address findById(Long id);

    /**
     * @return list of all addresses in database.
     */
    List<Address> findAll();
}
