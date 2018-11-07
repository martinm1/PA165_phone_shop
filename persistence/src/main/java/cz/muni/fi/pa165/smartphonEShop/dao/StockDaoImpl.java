/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Stock;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author martin
 */
@Repository
public class StockDaoImpl implements StockDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Stock stock)
    {
        if (stock == null){
            throw new IllegalArgumentException("Stock is null!");
        }
        em.persist(stock);
    }

    @Override
    public void update(Stock stock)
    {
        if (stock == null){
            throw new IllegalArgumentException("Stock is null!");
        }
        em.merge(stock);
    }

    @Override
    public void delete(Stock stock)
    {
        if (stock == null){
            throw new IllegalArgumentException("Stock is null!");
        }
        if(em.contains(stock)) em.remove(stock);
        else {
            Stock stock1 = em.getReference(stock.getClass(), stock.getId());
            em.remove(stock1);
        }
    }

    @Override
    public Stock findById(Long id)
    {
        if (id == null || id < 0){
            throw new IllegalArgumentException("StockId is null or less than 0!");
        }
        return em.find(Stock.class, id);
    }

    @Override
    public List<Stock> findAll()
    {
        return em.createQuery("SELECT q FROM Stock q", Stock.class).getResultList();
    }


}