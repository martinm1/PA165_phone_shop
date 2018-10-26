package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao
{
    @PersistenceContext
    EntityManager em;

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
            throw new IllegalArgumentException("Order is null or id si less then 0!");

        return em.find(Order.class, id);
    }

    @Override
    public List<Order> findAll()
    {
        TypedQuery<Order> query = em.createQuery("SELECT q FROM Order q", Order.class);
        return query.getResultList();
    }


}
