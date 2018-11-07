package cz.muni.fi.pa165.smartphonEShop.dao;

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
    public void create(Stock stock);

    /**
     * Update stock in database.
     * @param stock to be updated.
     * @throws IllegalArgumentException when stock is null.
     */
    public void update(Stock stock);

    /**
     * Delete stock in database.
     * @param stock to be deleted.
     * @throws IllegalArgumentException when stock is null.
     */
    public void delete(Stock stock);

    /**
     * Find stock with specific id in database.
     * @param id primary key for stock.
     * @return stock with given id, null if no such exists.
     * @throws IllegalArgumentException when id is null or less then 0.
     */
    public Stock findById(Long id);

    /**
     * @return List of all stocks in database.
     */
    public List<Stock> findAll();
}
