package cz.muni.fi.pa165.smartphonEShop.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.OrderDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;

import java.time.LocalDate;
import java.util.Collection;

/**
 *
 * @author martin
 */
public interface OrderFacade {
    /**
     * Find order with specific id
     * @param id primary key for order
     * @return Order with given id.
     */
    OrderDTO findOrderById(Long id);

    /**
     * Find orders with specific state.
     * @param state of order.
     * @return Collection of orders with given state.
     */
    Collection<OrderDTO> findOrdersByOrderState(OrderState state);

    /**
     * Find orders with specific order date.
     * @param orderDate of order.
     * @return Collection of orders with given order date.
     */
    Collection<OrderDTO> findOrdersByOrderDate(LocalDate orderDate);
    
    /**
     * Find orders with specific person.
     * @param personId of order.
     * @return Collection of orders with given person.
     */
    Collection<OrderDTO> findOrdersByPersonId(Long personId);
    
    /**
     * Find orders with specific phone.
     * @param phoneId of order.
     * @return Collection of orders with given phone.
     */
    Collection<OrderDTO> findOrdersByPhoneId(Long phoneId);
    
    /**
     * Find all orders.
     * @return Collection of all orders.
     */
    Collection<OrderDTO> getAllOrders();

        
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
     */
    Long createOrder(OrderDTO order);
}
