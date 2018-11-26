package cz.muni.fi.pa165.smartphonEShop.service.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.PersonAuthDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.PersonDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.entity.Person;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import cz.muni.fi.pa165.smartphonEShop.facade.PersonFacade;
import cz.muni.fi.pa165.smartphonEShop.service.service.BeanMappingService;
import cz.muni.fi.pa165.smartphonEShop.service.service.OrderService;
import cz.muni.fi.pa165.smartphonEShop.service.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by Jakub Ondrusek
 * Class represents: implementation of PersonFacade
 */
@Service
@Transactional
public class PersonFacadeImpl implements PersonFacade {

    @Autowired
    private PersonService personService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BeanMappingService beanMappingService;
    @Override
    public PersonDTO findPersonById(Long id) {
        Person phone = personService.findPersonById(id);
        return (phone == null) ? null : beanMappingService.mapTo(phone,PersonDTO.class);
    }

    @Override
    public PersonDTO findPersonByEmail(String email) {
        Person phone = personService.findPersonByEmail(email);
        return (phone == null) ? null : beanMappingService.mapTo(phone,PersonDTO.class);
    }

    @Override
    public PersonDTO findPersonByPhoneNumber(String phoneNumber) {
        Person phone = personService.findPersonByPhoneNumber(phoneNumber);
        return (phone == null) ? null : beanMappingService.mapTo(phone,PersonDTO.class);
    }

    @Override
    public Collection<PersonDTO> getAllPeople() {
        return beanMappingService.mapTo(personService.getAllPeople(),PersonDTO.class);
    }

    @Override
    public Collection<PersonDTO> getPeopleByPersonType(PersonType personType) {
        return beanMappingService.mapTo(personService.getPeopleByPersonType(personType),PersonDTO.class);
    }

    @Override
    public void addOrder(Long personId, Long orderId) {
        //personService.addOrder(personService.findPersonById(personId),orderService.findOrderById(orderId));
        personService.addOrder(personId, orderId);
    }

    @Override
    public void removeOrder(Long personId, Long orderId) {
        //personService.removeOrder(personService.findPersonById(personId), orderService.findOrderById(orderId));
        personService.removeOrder(personId, orderId);
    }

    @Override
    public void registerPerson(PersonDTO person, String pass) {
        Person guest = new Person();

        Address address = new Address();
        address.setStreetName(person.getAddress().getStreetName());
        address.setStreetNumber(person.getAddress().getStreetName());
        address.setCountry(person.getAddress().getCountry());
        address.setCity(person.getAddress().getCity());

        guest.setEmail(person.getEmail());
        guest.setAddress(address);
        guest.setDateOfBirth(person.getDateOfBirth());
        guest.setFirstName(person.getFirstName());
        guest.setGender(person.getGender());
        guest.setLastName(person.getLastName());
        guest.setPersonType(person.getPersonType());
        guest.setPhoneNumber(person.getPhoneNumber());

        personService.registerPerson(guest, pass);
    }

    @Override
    public boolean auth(PersonAuthDTO person)
    {
        return personService.auth(personService.findPersonById(person.getPersonID()), person.getPass());
    }
}
