package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
import cz.muni.fi.pa165.smartphonEShop.entity.Stock;

import java.util.List;

/**
 * Created by Roman Nahalka
 * Interface represents: Manager for StockDaoImpl
 */
public interface StockDao
{
    /**
     * Create new stock in database.
     * @param stock to be created.
     * @throws IllegalArgumentException when stock is null.
     */
    void create(Stock stock);

    /**
     * Update stock in database.
     * @param stock to be updated.
     * @throws IllegalArgumentException when stock is null.
     */
    void update(Stock stock);

    /**
     * Delete stock in database.
     * @param stock to be deleted.
     * @throws IllegalArgumentException when stock is null.
     */
    void delete(Stock stock);

    /**
     * Find stock with specific id in database.
     * @param id primary key for stock.
     * @return stock with given id, null if no such exists.
     * @throws IllegalArgumentException when id is null or less then 0.
     */
    Stock findById(Long id);
    
    /**
     * Find stock with specific name in database.
     * @param name of stock.
     * @return stock with given name, null if no such exists.
     */
    Stock findByName(String name);

    /**
     * Find stock with specific address id in database.
     * @param addressId primary key of address.
     * @return stock with given address id.
     */
    Stock findByAddressId(Long addressId);

    /**
     * Find stock with specific phone id in database.
     * @param phoneId primary key of phone.
     * @return stock with given phone id.
     */
    Stock findByPhoneId(Long phoneId);

    /**
     * @return List of all stocks in database.
     */
    List<Stock> findAll();
}
