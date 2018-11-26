/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import cz.muni.fi.pa165.smartphonEShop.exceptions.DAOException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author martin
 */
@Repository
public class PhoneDaoImpl implements PhoneDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Phone phone)
    {
        if (phone == null){
            throw new DAOException("Phone is null!");
        }
        em.persist(phone);
    }

    @Override
    public void update(Phone phone)
    {
        if (phone == null){
            throw new DAOException("Phone is null!");
        }
        em.merge(phone);
    }

    @Override
    public void delete(Phone phone)
    {
        if (phone == null){
            throw new DAOException("Phone is null!");
        }
        if(em.contains(phone)) em.remove(phone);
        else {
            Phone phone1 = em.getReference(phone.getClass(), phone.getId());
            em.remove(phone1);
        }
    }

    @Override
    public Phone findById(Long id)
    {
        if (id == null || id < 0){
            throw new DAOException("PhoneId is null or less than 0!");
        }
        return em.find(Phone.class, id);
    }

    @Override
    public List<Phone> findAll()
    {
        TypedQuery<Phone> query = em.createQuery("SELECT q FROM Phone q", Phone.class);
        return query.getResultList();
    }

    @Override
    public List<Phone> findPhonesByModelName(String modelName) {
        if (modelName == null){
            throw new DAOException("ModelName is null or less than 0!");
        }
        return em.createQuery("SELECT p FROM Phone p WHERE p.modelName =: modelName", Phone.class)
                                .setParameter("modelName", modelName).getResultList();
    }

    @Override
    public List<Phone> findPhonesByPriceInterval(int lowerBound, int upperBound) {
        if (lowerBound > upperBound){
            throw new DAOException("lowerBound is higher then upperBound");
        }
        return em.createQuery("SELECT p FROM Phone p WHERE p.price <: upperBound AND p.price>: lowerBound", Phone.class)
                                .setParameter("lowerBound", lowerBound).setParameter("upperBound", upperBound).getResultList();
    }

    @Override
    public List<Phone> findPhonesByTechnicalInfo(String technicalInfo) {
        if (technicalInfo == null){
            throw new DAOException("technicalInfo is null or less than 0!");
        }
        return em.createQuery("SELECT p FROM Phone p WHERE p.technicalInfo =: technicalInfo", Phone.class)
                                .setParameter("technicalInfo", technicalInfo).getResultList();
    }

    @Override
    public List<Phone> findPhonesByManufacturer(Manufacturer manufacturer) {
        if (manufacturer == null){
            throw new DAOException("Manufacturer is null or less than 0!");
        }
        return em.createQuery("SELECT p FROM Phone p WHERE p.manufacturer =: manufacturer", Phone.class)
                                .setParameter("manufacturer", manufacturer).getResultList();
    }

    @Override
    public List<Phone> findPhonesByStockId(Long stockId) {
        return em.createQuery("SELECT p FROM Phone p JOIN p.stock s where s.id =: stockId", Phone.class)
                                .setParameter("stockId", stockId).getResultList();
    }


}
