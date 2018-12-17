package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.dto.PersonDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.entity.Person;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import java.util.List;

/**
 * Created by Stefan Holecko
 * Class represents: PersonService interface
 */

public interface PersonService {


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

    /**
     * Add specific order to specific person.
     * @param person represents person.
     * @param order represents order.
     */
    //void addOrder(Person person, Order order);
    
    /**
     * Add specific order to specific person.
     * @param personID id of person.
     * @param orderID id of order.
     */
    void addOrder(Long personID, Long orderID);

    /**
     * Register new person to system.
     * @param person New person.
     */
    Long registerPerson(Person person, String pass);

    /**
     * Add specific order to specific person.
     * @param person represents person.
     * @param order represents order.
     */
    //void removeOrder(Person person, Order order);

    /**
     * Add specific order to specific person.
     * @param personID id of person.
     * @param orderID id of order.
     */
    void removeOrder(Long personID, Long orderID);

    /**
     * Authenticate user.
     * @param email represent person email.
     * @param pass to be checked.
     * @return true, if password matches the records.
     */
    boolean auth(String email, String pass);

}
