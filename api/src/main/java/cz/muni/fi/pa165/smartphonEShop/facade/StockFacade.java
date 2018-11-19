package cz.muni.fi.pa165.smartphonEShop.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.StockDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Address;

import java.util.Collection;

/**
 * Created by Stefan Holecko
 * Class represents:
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
     * @return Stock with given address.
     */
    StockDTO findStockByAddressId(Long addressId);
    

    /**
     * Find all stocks.
     * @return Collection of all stocks.
     */
    Collection<StockDTO> getAllStocks();


}
