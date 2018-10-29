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


}
