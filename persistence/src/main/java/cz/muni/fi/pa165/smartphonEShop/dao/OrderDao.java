package cz.muni.fi.pa165.smartphonEShop.dao;
import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import cz.muni.fi.pa165.smartphonEShop.exceptions.DAOException;

/**
 * Created by Stefan Holecko
 * Interface represents: Manager for OrderDaoImpl
 */
public interface OrderDao {
    /**
     * Create new order in database
     * @param order to be created.
     * @throws DAOException when order is null.
     */
    void create(Order order);


    /**
     * Update order in database
     * @param order to be updated
     * @throws DAOException when order is null.
     */
    void update(Order order);


    /**
     * Delete order from database
     * @param order to be removed
     * @throws DAOException when order is null.
     */
    void delete(Order order);


    /**
     * Find order with specified ID in database.
     * @param id primary key of requested order.
     * @return order with given id, null if no such exists.
     * @throws DAOException when id is null or less than 0.
     */
    Order findById(Long id);


    /**
     * @return List of all orders from database
     */
    List<Order> findAll();
    
    /**
     * Find orders with specific state.
     * @param state of order.
     * @return Collection of orders with given state.
     * @throws DAOException when state is null.
     */
    List<Order> findOrdersByOrderState(OrderState state);
    
    /**
     * Find orders with specific order date.
     * @param orderDate of order.
     * @return Collection of orders with given order date.
     * @throws DAOException when orderDate is null.
     */
    List<Order> findOrdersByOrderDate(LocalDate orderDate);
    
    /**
     * Find orders with specific person.
     * @param personId of order.
     * @return Collection of orders with given person.
     * @throws DAOException when personId is null or less than 0.
     */
    List<Order> findOrdersByPersonId(Long personId);
    
    /**
     * Find orders with specific phone.
     * @param phoneId of order.
     * @return Collection of orders with given phone.
     * @throws DAOException when phoneId is null or less than 0.
     */
    List<Order> findOrdersByPhoneId(Long phoneId);
}
