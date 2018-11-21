package cz.muni.fi.pa165.smartphonEShop.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.StockDTO;
import java.util.Collection;

/**
 * Created by Stefan Holecko
 * Class represents: StockFacade interface
 */

public interface StockFacade {

    /**
     * Find stock with specific id
     * @param id primary key for stock
     * @return Stock with given id.
     */
    StockDTO findStockById(Long id);

    /**
     * Find stock with specific name
     * @param name of the  stock
     * @return Stock with given name.
     */
    StockDTO findStockByName(String name);

    /**
     * Find stock with specific addressId
     * @param addressId of the  stock
     * @return Stock with given addressId.
     */
    StockDTO findStockByAddressId(Long addressId);

    /**
     * Find stock with specific phoneId
     * @param phoneId of the  stock
     * @return Stock with given phoneId.
     */
    StockDTO findStockByPhoneId(Long phoneId);

    /**
     * Find all stocks.
     * @return Collection of all stocks.
     */
    Collection<StockDTO> getAllStocks();

    /**
     * Register new stock to system.
     * @param stock New stock.
     */
    Long createStock(StockDTO stock);

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
