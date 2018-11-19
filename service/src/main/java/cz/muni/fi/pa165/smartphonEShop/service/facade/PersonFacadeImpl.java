package cz.muni.fi.pa165.smartphonEShop.service.facade;

import cz.muni.fi.pa165.smartphonEShop.dao.PersonDao;
import cz.muni.fi.pa165.smartphonEShop.dao.PersonDaoImpl;
import cz.muni.fi.pa165.smartphonEShop.dto.PersonDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Person;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import cz.muni.fi.pa165.smartphonEShop.facade.PersonFacade;
import cz.muni.fi.pa165.smartphonEShop.service.BeanMappingService;
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
        personService.addOrder(personService.findPersonById(personId),orderService.findOrderById(orderId));

    }

    @Override
    public void registerPerson(Person person) {
        personService.registerPerson(person);

    }
}
