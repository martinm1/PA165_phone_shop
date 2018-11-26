package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.dao.ClaimDao;
import cz.muni.fi.pa165.smartphonEShop.dao.OrderDao;
import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author martin
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDao orderDao;
    
    @Autowired
    private ClaimDao claimDao;
    
    @Override
    public Order findOrderById(Long id) {
        return orderDao.findById(id);
    }

    @Override
    public List<Order> findOrdersByOrderState(OrderState state) {
        return orderDao.findOrdersByOrderState(state);
    }

    @Override
    public List<Order> findOrdersByOrderDate(LocalDate orderDate) {
        return orderDao.findOrdersByOrderDate(orderDate);
    }

    @Override
    public List<Order> findOrdersByPersonId(Long personId) {
        return orderDao.findOrdersByPersonId(personId);
    }

    @Override
    public List<Order> findOrdersByPhoneId(Long phoneId) {
        return orderDao.findOrdersByPhoneId(phoneId);
    }
   

    @Override
    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    @Override
    public void addClaim(Long orderId, Long claimId) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        orderDao.findById(orderId).addClaim(claimDao.findById(claimId));
    }

    @Override
    public Long createOrder(Order order) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        orderDao.create(order);
        return order.getId();
    }

    @Override
    public void removeClaim(Long orderId, Long claimId) {
        orderDao.findById(orderId).removeClaim(claimDao.findById(claimId));
    }
}
