package cz.muni.fi.pa165.smartphonEShop.service;

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
     */
    void createStock(Stock stock);

    /**
     * Find stock with specific id.
     * @param id primary key for stock.
     * @return Stock with given id.
     */
    Stock findStockById(Long id);

    /**
     * Find all stocks.
     * @return List of all stocks.
     */
    List<Stock> findAllStocks();
}
