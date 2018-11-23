package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.dao.PersonDao;
import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.entity.Person;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Created by Jakub Ondrusek
 * Class represents: implementation of PersonService
 */

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDao personDao;


    @Override
    public Person findPersonById(Long id) {
        return personDao.findById(id);
    }

    @Override
    public Person findPersonByEmail(String email) {
        return personDao.findByEmail(email);
    }

    @Override
    public Person findPersonByPhoneNumber(String phoneNumber) {
        return personDao.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<Person> getAllPeople() {
        return personDao.findAll();
    }

    @Override
    public List<Person> getPeopleByPersonType(PersonType personType) {
        return personDao.findByPersonType(personType);
    }

    @Override
    public void addOrder(Person person, Order order) {
        //TODO:: add method to PersonDAO

    }

    @Override
    public void registerPerson(Person person) {
        personDao.create(person);
    }

    @Override
    public void removeOrder(Person person, Order order) {
        personDao.create(person);
    }
}
