package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.dao.OrderDao;
import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import java.time.LocalDate;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author martin
 */
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDao orderDao;
    
    @Override
    public Order findOrderById(Long id) {
        return orderDao.findById(id);
    }

    @Override
    public Collection<Order> findOrdersByOrderState(OrderState state) {
        return orderDao.findOrdersByOrderState(state);
    }

    @Override
    public Collection<Order> findOrdersByOrderDate(LocalDate orderDate) {
        return orderDao.findOrdersByOrderDate(orderDate);
    }

    @Override
    public Collection<Order> findOrdersByPerson(String personId) {
        return orderDao.findOrdersByPerson(personId);
    }

    @Override
    public Collection<Order> findOrdersByPhone(Long phoneId) {
        return orderDao.findOrdersByPhone(phoneId);
    }

    @Override
    public Collection<Order> getAllOrders() {
        return orderDao.findAll();
    }

    @Override
    public void addClaim(Long orderId, Long claimId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registerOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
