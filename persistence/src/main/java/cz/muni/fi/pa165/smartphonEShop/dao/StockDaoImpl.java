/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Stock;
import org.springframework.stereotype.Repository;
import cz.muni.fi.pa165.smartphonEShop.exceptions.DAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.NoResultException;

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
            throw new DAOException("Stock is null!");
        }
        em.persist(stock);
    }

    @Override
    public void update(Stock stock)
    {
        if (stock == null){
            throw new DAOException("Stock is null!");
        }
        em.merge(stock);
    }

    @Override
    public void delete(Stock stock)
    {
        if (stock == null){
            throw new DAOException("Stock is null!");
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
            throw new DAOException("StockId is null or less than 0!");
        }
        return em.find(Stock.class, id);
    }

    @Override
    public List<Stock> findAll()
    {
        return em.createQuery("SELECT q FROM Stock q", Stock.class).getResultList();
    }

    @Override
    public Stock findByName(String name) {
        if (name == null)
            throw new DAOException("Stock name is null!");

        try
        {
            return em.createQuery("SELECT p FROM Stock p WHERE p.name =: name", Stock.class)
                                                .setParameter("name", name).getSingleResult();
        }
        catch (NoResultException ex)
        {
            return null;
        }
    }

    @Override
    public Stock findByAddressId(Long addressId)
    {
        if (addressId == null)
            throw new DAOException("Address id is null!");

        try
        {
            return em.createQuery("SELECT s FROM Stock s JOIN s.address a WHERE a.id =: addressId", Stock.class)
                                    .setParameter("addressId", addressId).getSingleResult();
        }

        catch (NoResultException ex)
        {
            return null;
        }
    }

    @Override
    public Stock findByPhoneId(Long phoneId)
    {
        if (phoneId == null)
            throw new DAOException("Phone id is null!");

        try
        {
            return em.createQuery("SELECT s FROM Stock s JOIN s.phones p WHERE p.id =: phoneId", Stock.class)
                                    .setParameter("phoneId", phoneId).getSingleResult();
        }

        catch (NoResultException ex)
        {
            return null;
        }
    }
}
