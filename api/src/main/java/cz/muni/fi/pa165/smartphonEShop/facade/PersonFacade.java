package cz.muni.fi.pa165.smartphonEShop.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.PersonAuthDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.PersonDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;

import java.util.Collection;

/**
 * Created by Roman Nahalka
 * Class represents: PersonFacade interface.
 * @author xnahalka
 */

public interface PersonFacade
{
    /**
     * Find person with specific id
     * @param id primary key for person
     * @return Person with given id.
     */
    PersonDTO findPersonById(Long id);

    /**
     * Find person with specific email address.
     * @param email of person.
     * @return Person with given email.
     */
    PersonDTO findPersonByEmail(String email);

    /**
     * Find person with specific phone number.
     * @param phoneNumber of person.
     * @return Person with given phone number.
     */
    PersonDTO findPersonByPhoneNumber (String phoneNumber);

    /**
     * Find all people.
     * @return Collection of all people.
     */
    Collection<PersonDTO> getAllPeople();

    /**
     * Find all people with specific type.
     * @param personType of people.
     * @return Collection of people with given person type.
     */
    Collection<PersonDTO> getPeopleByPersonType(PersonType personType);

    /**
     * Add specific order to specific person.
     * @param personId primary key for person.
     * @param orderId primary key for order.
     */
    void addOrder(Long personId, Long orderId);

    /**
     * Remove specific order from specific person.
     * @param personId primary key for person.
     * @param orderId primary key for order.
     */
    void removeOrder(Long personId, Long orderId);

    /**
     * Register new person to system.
     * @param person New person.
     */
    void registerPerson(PersonDTO person, String pass);

    /**
     * Authenticate user.
     * @return true, if password matches the records.
     */
    boolean auth(PersonAuthDTO person);
}
