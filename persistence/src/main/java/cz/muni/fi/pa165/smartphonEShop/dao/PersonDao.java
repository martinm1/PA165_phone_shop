package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Person;

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
     * @return List of all people from database
     */
    public List<Person> findAll();
}
