package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.entity.Stock;

import java.util.List;

/**
 * Created by Roman Nahalka
 * Class represents: StockService interface.
 */
public interface StockService
{
    /**
     * Add new stock to database.
     * @param stock to be created.
     * @return id of created stock.
     */
    Long createStock(Stock stock);

    /**
     * Find stock with specific id.
     * @param id primary key for stock.
     * @return Stock with given id.
     */
    Stock findStockById(Long id);
    
    /**
     * Find stock with specific name.
     * @param name of stock.
     * @return Stock with given name.
     */
    Stock findStockByName(String name);
    
    /**
     * Find stock with specific address ID.
     * @param addressId of stock.
     * @return Stock with given addressId.
     */
    Stock findStockByAddressId(Long addressId);
    
    /**
     * Find stock with specific phone ID.
     * @param phoneId of stock.
     * @return Stock with given phoneId.
     */
    Stock findStockByPhoneId(Long phoneId);

    /**
     * Find all stocks.
     * @return List of all stocks.
     */
    List<Stock> getAllStocks();

    /**
     * Add specific phone to specific stock.
     * @param phoneId primary key for phone.
     * @param stockId primary key for stockId.
     */
    void addPhone(Long stockId, Long phoneId);


    /**
     * Remove specific phone from specific stock.
     * @param phoneId primary key for phone.
     * @param stockId primary key for stockId.
     */
    void removePhone(Long stockId, Long phoneId);
}
