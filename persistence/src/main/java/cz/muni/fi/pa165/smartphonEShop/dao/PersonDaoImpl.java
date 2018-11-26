package cz.muni.fi.pa165.smartphonEShop.dao;
import cz.muni.fi.pa165.smartphonEShop.entity.Person;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import cz.muni.fi.pa165.smartphonEShop.exceptions.DAOException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;



@Repository
public class PersonDaoImpl implements PersonDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Person person) {
        if (person == null){
            throw new DAOException("claim is null!");
        }
        entityManager.persist(person);
    }

    @Override
    public void update(Person person) {
        if (person == null){
            throw new DAOException("Address is null!");
        }
        entityManager.merge(person);
    }

    @Override
    public void delete(Person person) {
        if (person == null){
            throw new DAOException("Address is null!");
        }
        entityManager.remove(person);
    }

    @Override
    public Person findById(Long id) {
        if (id == null || id < 0){
            throw new DAOException("AddressId is null or less than 0!");
        }
        return entityManager.find(Person.class, id);
    }

    @Override
    public Person findByEmail(String email)
    {
        if (email == null || email.isEmpty())
            throw new DAOException("Email is null!");

        try
        {
            return entityManager.createQuery("SELECT p FROM Person p WHERE p.email =: email", Person.class)
                                                .setParameter("email", email).getSingleResult();
        }
        catch (NoResultException ex)
        {
            return null;
        }
    }

    @Override
    public Person findByPhoneNumber(String number)
    {
        if (number == null || number.isEmpty())
            throw new DAOException("Phone number is null!");

        try
        {
            return entityManager.createQuery("SELECT p FROM Person p WHERE p.phoneNumber =: number", Person.class)
                                                .setParameter("number", number).getSingleResult();
        }

        catch (NoResultException ex)
        {
            return null;
        }
    }

    @Override
    public List<Person> findByPersonType(PersonType type)
    {
        if (type == null)
            throw new DAOException("Person type is null!");

        return entityManager.createQuery("SELECT p FROM Person p WHERE p.personType =: type", Person.class)
                                                                .setParameter("type", type).getResultList();
    }

    @Override
    public List<Person> findAll() {
        return entityManager.createQuery("select p from Person p", Person.class).getResultList();
    }
}
