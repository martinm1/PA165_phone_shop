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
            throw new IllegalArgumentException("Phone is null!");
        }
        em.persist(phone);
    }

    @Override
    public void update(Phone phone)
    {
        if (phone == null){
            throw new IllegalArgumentException("Phone is null!");
        }
        em.merge(phone);
    }

    @Override
    public void delete(Phone phone)
    {
        if (phone == null){
            throw new IllegalArgumentException("Phone is null!");
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
            throw new IllegalArgumentException("PhoneId is null or less than 0!");
        }
        return em.find(Phone.class, id);
    }

    @Override
    public List<Phone> findAll()
    {
        TypedQuery<Phone> query = em.createQuery("SELECT q FROM Phone q", Phone.class);
        return query.getResultList();
    }


}
