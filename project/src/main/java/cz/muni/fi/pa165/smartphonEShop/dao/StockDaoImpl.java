/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Stock;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author martin
 */
@Repository
public class StockDaoImpl implements StockDao{
    @PersistenceContext
    EntityManager em;

    @Override
    public void create(Stock stock)
    {
        em.persist(stock);
    }

    @Override
    public void update(Stock stock)
    {
        em.merge(stock);
    }

    @Override
    public void delete(Stock stock)
    {
        em.remove(stock);
    }

    @Override
    public Stock findById(Long id)
    {
        return em.find(Stock.class, id);
    }

    @Override
    public List<Stock> findAll()
    {
        TypedQuery<Stock> query = em.createQuery("SELECT q FROM Stock q", Stock.class);
        return query.getResultList();
    }


}
