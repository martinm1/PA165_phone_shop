package cz.muni.fi.pa165.smartphonEShop.dao;
import cz.muni.fi.pa165.smartphonEShop.entity.Order;

import java.util.List;

/**
 * Created by Stefan Holecko
 * Interface represents: Manager for OrderDaoImpl
 */
public interface OrderDao {
    /**
     * Create new order in database
     * @param order to be created.
     * @throws IllegalArgumentException when order is null.
     */
    public void create(Order order);


    /**
     * Update order in database
     * @param order to be updated
     * @throws IllegalArgumentException when order is null.
     */
    public void update(Order order);


    /**
     * Delete order from database
     * @param order to be removed
     * @throws IllegalArgumentException when order is null.
     */
    public void delete(Order order);


    /**
     * Find order with specified ID in database.
     * @param id primary key of requested order.
     * @return order with given id, null if no such exists.
     * @throws IllegalArgumentException when id is null or less than 0.
     */
    public Order findById(Long id);


    /**
     * @return List of all orders from database
     */
    public List<Order> findAll();
}
