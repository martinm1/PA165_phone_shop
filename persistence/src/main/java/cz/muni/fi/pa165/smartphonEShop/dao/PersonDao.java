package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Person;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;

import java.util.List;

/**
 * Created by Stefan Holecko
 * Interface represents: Manager for PersonDaoImpl
 */
public interface PersonDao {

    /**
     * Create new person in database
     * @param person to be created.
     * @throws IllegalArgumentException when person is null.
     */
    public void create(Person person);


    /**
     * Update person in database
     * @param person to be updated
     * @throws IllegalArgumentException when person is null.
     */
    public void update(Person person);


    /**
     * Delete person from database
     * @param person to be removed
     * @throws IllegalArgumentException when person is null.
     */
    public void delete(Person person);


    /**
     * Find person with specified ID in database.
     * @param id primary key of requested person.
     * @return person with given id, null if no such exists.
     * @throws IllegalArgumentException when id is null or less than 0.
     */
    public Person findById(Long id);

    /**
     * Find person with specified email in database.
     * @param email of requested person.
     * @return person with given email, null if no such exists.
     * @throws IllegalArgumentException when email is null.
     */
    public Person findByEmail(String email);

    /**
     * Find person with specified phone number in database.
     * @param number of requested person.
     * @return person with given phone number, null if no such exists.
     * @throws IllegalArgumentException when number is null.
     */
    public Person findByPhoneNumber(String number);

    /**
     * Find people with specified person type.
     * @param type of requested people.
     * @return people with given person type.
     * @throws IllegalArgumentException when type is null.
     */
    public List<Person> findByPersonType(PersonType type);


    /**
     * @return List of all people from database
     */
    public List<Person> findAll();
}
