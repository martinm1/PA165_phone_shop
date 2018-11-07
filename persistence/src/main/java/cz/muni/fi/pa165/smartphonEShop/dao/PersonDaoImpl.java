package cz.muni.fi.pa165.smartphonEShop.dao;
import cz.muni.fi.pa165.smartphonEShop.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class PersonDaoImpl implements PersonDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Person person) {
        if (person == null){
            throw new IllegalArgumentException("claim is null!");
        }
        entityManager.persist(person);
    }

    @Override
    public void update(Person person) {
        if (person == null){
            throw new IllegalArgumentException("Address is null!");
        }
        entityManager.merge(person);
    }

    @Override
    public void delete(Person person) {
        if (person == null){
            throw new IllegalArgumentException("Address is null!");
        }
        entityManager.remove(person);
    }

    @Override
    public Person findById(Long id) {
        if (id == null || id < 0){
            throw new IllegalArgumentException("AddressId is null or less than 0!");
        }
        return entityManager.find(Person.class, id);
    }

    @Override
    public List<Person> findAll() {
        return entityManager.createQuery("select p from Person p", Person.class).getResultList();
    }
}
