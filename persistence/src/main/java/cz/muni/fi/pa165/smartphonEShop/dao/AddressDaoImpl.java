package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.enums.AddressEnum;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;

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
            throw new IllegalArgumentException("Address is null!");
        }
        entityManager.persist(address);
    }

    @Override
    public void update(Address address) {
        if (address == null){
            throw new IllegalArgumentException("Address is null!");
        }
        entityManager.merge(address);
    }

    @Override
    public void delete(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address is null!");
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
            throw new IllegalArgumentException("AddressId is null or less than 0!");
        }
        return entityManager.find(Address.class, id);
    }

    @Override
    public List<Address> findAll() {
        return entityManager.createQuery("select a from Address a", Address.class)
                .getResultList();
    }

    @Override
    //TODO
    public List<Address> findAllAddressesBy(HashMap<AddressEnum, String> specificator) {
        return null;
    }
}
