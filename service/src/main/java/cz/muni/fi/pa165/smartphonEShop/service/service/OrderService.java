package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;

import java.time.LocalDate;
import java.util.List;


/**
 * Created by Jakub Ondrusek
 * Class represents: OrderService interface
 */
public interface OrderService {
    /**
     * Find order with specific id
     * @param id primary key for order
     * @return Order with given id.
     */
    Order findOrderById(Long id);

    /**
     * Find orders with specific state.
     * @param state of order.
     * @return Collection of orders with given state.
     */
    List<Order> findOrdersByOrderState(OrderState state);

    /**
     * Find orders with specific order date.
     * @param orderDate of order.
     * @return Collection of orders with given order date.
     */
    List<Order> findOrdersByOrderDate(LocalDate orderDate);

    /**
     * Find orders with specific person.
     * @param personId of order.
     * @return Collection of orders with given person.
     */
    List<Order> findOrdersByPersonId(Long personId);

    /**
     * Find orders with specific phone.
     * @param phoneId of order.
     * @return Collection of orders with given phone.
     */
    List<Order> findOrdersByPhoneId(Long phoneId);

    /**
     * Find all orders.
     * @return Collection of all orders.
     */
    List<Order> getAllOrders();

    /**
     * Add specific claim to specific order.
     * @param orderId primary key for order.
     * @param claimId primary key for claim.
     */
    void addClaim(Long orderId, Long claimId);

    /**
     * Add specific claim to specific order.
     * @param orderId primary key for order.
     * @param claimId primary key for claim.
     */
    void removeClaim(Long orderId, Long claimId);

    /**
     * Register new order to system.
     * @param order New order.
     * @return id of created order.
     */
    Long createOrder(Order order);
}
