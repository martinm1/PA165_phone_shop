package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.enums.AddressEnum;
import org.springframework.stereotype.Repository;

import cz.muni.fi.pa165.smartphonEShop.exceptions.DAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;


/**
 * Created by Stefan Holecko
 * Class represents:
 */

@Repository
public class AddressDaoImpl implements AddressDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Address address) {
        if (address == null){
            throw new DAOException("Address is null!");
        }
        entityManager.persist(address);
    }

    @Override
    public void update(Address address) {
        if (address == null){
            throw new DAOException("Address is null!");
        }
        entityManager.merge(address);
    }

    @Override
    public void delete(Address address) {
        if (address == null) {
            throw new DAOException("Address is null!");
        }
        if (entityManager.contains(address)) {
            entityManager.remove(address);

        } else {
            Address a = entityManager.getReference(address.getClass(),address.getId());
            entityManager.remove(a);
        }
    }

    @Override
    public Address findById(Long id) {
        if (id == null || id < 0){
            throw new DAOException("AddressId is null or less than 0!");
        }
        return entityManager.find(Address.class, id);
    }

    @Override
    public List<Address> findAll() {
        return entityManager.createQuery("select a from Address a", Address.class)
                .getResultList();
    }

    @Override
    public List<Address> findAllAddressesBy(HashMap<AddressEnum, String> specificator) {
        List<Address> list1 = new ArrayList<>();
        List<Address> temp = new ArrayList<>();
        if(specificator.containsKey(AddressEnum.CITY)) {

            temp.addAll(entityManager.createQuery("select a from Address a where a.city = :city", Address.class).
                    setParameter("city", specificator.get(AddressEnum.CITY)).
                    getResultList());
                if (list1.isEmpty())
                    list1= new ArrayList<>(temp);
                else
                    list1.retainAll(temp);
                temp.clear();

        }

        if(specificator.containsKey(AddressEnum.COUNTRY)) {
            temp.addAll(entityManager.createQuery("select a from Address a where a.country = :country", Address.class).
                    setParameter("country", specificator.get(AddressEnum.COUNTRY)).
                    getResultList());
                if (list1.isEmpty())
                    list1= new ArrayList<>(temp);
                else
                    list1.retainAll(temp);
                temp.clear();

        }
        if(specificator.containsKey(AddressEnum.STREET_NAME)) {
            temp.addAll(entityManager.createQuery("select a from Address a where a.streetName = :streetName", Address.class).
                    setParameter("streetName", specificator.get(AddressEnum.STREET_NAME)).
                    getResultList());
                if (list1.isEmpty())
                    list1= new ArrayList<>(temp);
                else
                    list1.retainAll(temp);
                temp.clear();
        }

        if(specificator.containsKey(AddressEnum.STREET_NUMBER)) {
            temp.addAll(entityManager.createQuery("select a from Address a where a.streetNumber = :streetNumber", Address.class).
                    setParameter("streetNumber", specificator.get(AddressEnum.STREET_NUMBER)).
                    getResultList());
                if (list1.isEmpty())
                    list1= new ArrayList<>(temp);
                else
                    list1.retainAll(temp);
                temp.clear();

        }
        return list1;

    }

}
