package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import java.time.LocalDate;
import java.util.Collection;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import javax.persistence.NoResultException;

@Repository
public class OrderDaoImpl implements OrderDao
{
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Order order)
    {
        if (order == null)
            throw new IllegalArgumentException("Order is null!");

        em.persist(order);
    }

    @Override
    public void update(Order order)
    {
        if (order == null)
            throw new IllegalArgumentException("Order is null!");

        em.merge(order);
    }

    @Override
    public void delete(Order order)
    {
        if (order == null)
            throw new IllegalArgumentException("Order is null!");

        em.remove(order);
    }

    @Override
    public Order findById(Long id)
    {
        if (id == null || id < 0)
            throw new IllegalArgumentException("ID is null or less than 0!");

        return em.find(Order.class, id);
    }

    @Override
    public List<Order> findAll()
    {
        return em.createQuery("select o from Order o", Order.class).getResultList();
    }

    @Override
    public List<Order> findOrdersByOrderState(OrderState state) {
        if (state == null)
            throw new IllegalArgumentException("State is null!");
        
        return em.createQuery("SELECT o FROM Order o WHERE o.state =: state", Order.class)
                                            .setParameter("state", state).getResultList();
    }

    @Override
    public List<Order> findOrdersByOrderDate(LocalDate orderDate) {
        if (orderDate == null)
            throw new IllegalArgumentException("Order date is null!");
        
        return em.createQuery("SELECT o FROM Order o WHERE o.orderDate =: orderDate", Order.class)
                                            .setParameter("orderDate", orderDate).getResultList();
    }

    @Override
    public List<Order> findOrdersByPersonId(Long personId) {
        if (personId == null)
            throw new IllegalArgumentException("Person id date is null!");
        
        return em.createQuery("SELECT o FROM Order o WHERE o.person.id =: personId", Order.class)
                                            .setParameter("personId", personId).getResultList();
    }

    @Override
    public List<Order> findOrdersByPhoneId(Long phoneId) {
        if (phoneId == null)
            throw new IllegalArgumentException("Phone id date is null!");
        
        return em.createQuery("SELECT o FROM Order o WHERE o.phone.id =: phoneId", Order.class)
                                            .setParameter("phoneId", phoneId).getResultList();
    }


}
