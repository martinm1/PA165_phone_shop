package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.entity.Person;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;

import java.util.Collection;
import java.util.List;

/**
 * Created by Stefan Holecko
 * Class represents:
 */

public interface PersonService {

    /**
     * Add new person to database.
     * @param person to be created.
     */
    void create(Person person);

    /**
     * Find person with specific id.
     * @param id primary key for person.
     * @return Person with given id.
     */
    Person findPersonById(Long id);

    /**
     * Find person with specific email address.
     * @param email of person.
     * @return Person with given email.
     */
    Person findPersonByEmail(String email);

    /**
     * Find person with specific phone number.
     * @param phoneNumber of person.
     * @return Person with given phone number.
     */
    Person findPersonByPhoneNumber (String phoneNumber);

    /**
     * Find all people.
     * @return Collection of all people.
     */
    List<Person> getAllPeople();

    /**
     * Find all people with specific type.
     * @param personType of people.
     * @return Collection of people with given person type.
     */
    List<Person> getPeopleByPersonType(PersonType personType);
    
}
