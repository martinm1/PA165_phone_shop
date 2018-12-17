package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.dao.OrderDao;
import cz.muni.fi.pa165.smartphonEShop.dao.PersonDao;
import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.entity.Person;
import cz.muni.fi.pa165.smartphonEShop.enums.Gender;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import cz.muni.fi.pa165.smartphonEShop.service.config.ServiceConfiguration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;

import org.springframework.test.context.ContextConfiguration;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author martin
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PersonServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private PersonDao personDao;
    
    @Mock
    private OrderDao orderDao;

    @Autowired
    @InjectMocks
    private PersonServiceImpl personService;

    private Person person1;
    private Person person2;
    
    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void prepare(){
        person1 = new Person();
        person2 = new Person();
        
        person1.setId(10L);
        person2.setId(20L);

        person1.setFirstName("Franta");
        person2.setFirstName("Jarda");

        person1.setLastName("Novák");
        person2.setLastName("Dvořák");
        
        person1.setEmail("zadny-takovyhle-novak-neexistuje12345@seznam.cz");
        person2.setEmail("zadny-takovyhle-dvorak-neexistuje12345@seznam.cz");
         
        person1.setPhoneNumber("123");
        person2.setPhoneNumber("456");
        
        person1.setDateOfBirth(LocalDate.ofYearDay(1, 2));
        person2.setDateOfBirth(LocalDate.ofYearDay(3, 4));
        
        person1.setGender(Gender.MALE);
        person2.setGender(Gender.FEMALE);
        
        person1.setPersonType(PersonType.ADMIN);
        person2.setPersonType(PersonType.SIGNED_USER);

        person1.setPasswordHash("ssss");
        person2.setPasswordHash("llll");
        
        Address address1 = new Address();
        Address address2 = new Address();

        address1.setStreetName("Botanická");
        address2.setStreetName("Ilkovičova");

        address1.setStreetNumber("68A");
        address2.setStreetNumber("2");
        
        address1.setCity("Brno");
        address2.setCity("Bratislava");
        
        address1.setCountry("Česko");
        address2.setCountry("Slovensko");
        
        person1.setAddress(address1);
        person2.setAddress(address2);
    
    }
    
    @Test
    public void findAddressById()
    {
        when(personDao.findById(person1.getId())).thenReturn(person1);
        when(personDao.findById(person2.getId())).thenReturn(person2);

        Person person = personService.findPersonById(person1.getId());
        Person person3 = personService.findPersonById(person2.getId());

        Assert.assertEquals(person, person1);
        Assert.assertEquals(person3, person2);
    }
    
    @Test
    public void findAddressByEmail()
    {
        when(personDao.findByEmail(person1.getEmail())).thenReturn(person1);
        when(personDao.findByEmail(person2.getEmail())).thenReturn(person2);

        Person person = personService.findPersonByEmail(person1.getEmail());
        Person person3 = personService.findPersonByEmail(person2.getEmail());

        Assert.assertEquals(person, person1);
        Assert.assertEquals(person3, person2);
    }
    
    @Test
    public void findPersonByPhoneNumber()
    {
        when(personDao.findByPhoneNumber(person1.getPhoneNumber())).thenReturn(person1);
        when(personDao.findByPhoneNumber(person2.getPhoneNumber())).thenReturn(person2);

        Person person = personService.findPersonByPhoneNumber(person1.getPhoneNumber());
        Person person3 = personService.findPersonByPhoneNumber(person2.getPhoneNumber());

        Assert.assertEquals(person, person1);
        Assert.assertEquals(person3, person2);
    }
    
    @Test
    public void getAllPeople()
    {
        List<Person> ret = Arrays.asList(person1, person2);
        when(personDao.findAll()).thenReturn(ret);

        List<Person> people = personService.getAllPeople();

        Assert.assertEquals(2, people.size());
        Assert.assertTrue(people.contains(person1));
        Assert.assertTrue(people.contains(person2));
    }
    
    @Test
    public void getPeopleByPersonType()
    {
        when(personDao.findByPersonType(PersonType.ADMIN)).thenReturn(Collections.singletonList(person1));
        when(personDao.findByPersonType(PersonType.SIGNED_USER)).thenReturn(Collections.singletonList(person2));

        List<Person> people = personService.getPeopleByPersonType(PersonType.ADMIN);

        Assert.assertEquals(1, people.size());
        Assert.assertTrue(people.contains(person1));

        people = personService.getPeopleByPersonType(PersonType.SIGNED_USER);

        Assert.assertEquals(1, people.size());
        Assert.assertTrue(people.contains(person2));
    }
    
    @Test
    public void addOrder(){
        
        Order order = new Order();
        Assert.assertTrue(!person1.getOrders().contains(order));
       
        when(orderDao.findById(order.getId())).thenReturn(order);
        when(personDao.findById(person1.getId())).thenReturn(person1);
        
        personService.addOrder(person1.getId(), order.getId());
        
        Assert.assertTrue(person1.getOrders().contains(order));
    }
    
    @Test
    public void registerPerson(){
        Person person = new Person();

        doAnswer(invocationOnMock ->
        {
            person.setId(40L);
            return 40L;
        }).when(personDao).create(person);

        personService.registerPerson(person, "heslo");

        Assert.assertNotNull(person.getId());
        Assert.assertEquals(person.getId().longValue(), 40L);
    }
    
    @Test
    public void removeOrder(){
        
        Order order = new Order();
        Assert.assertTrue(!person1.getOrders().contains(order));
       
        when(orderDao.findById(order.getId())).thenReturn(order);
        when(personDao.findById(person1.getId())).thenReturn(person1);
        
        personService.addOrder(person1.getId(), order.getId());
        
        Assert.assertTrue(person1.getOrders().contains(order));
        
        personService.removeOrder(person1.getId(), order.getId());
        
        Assert.assertTrue(!person1.getOrders().contains(order));
    }

    @Test
    public void authTest()
    {
        Person person = new Person();

        doAnswer(invocationOnMock ->
        {
            person.setId(40L);
            person.setEmail("neco@neco.cz");
            return 40L;
        }).when(personDao).create(person);

        personService.registerPerson(person, "heslo");
        when(personDao.findByEmail("neco@neco.cz")).thenReturn(person);

        Assert.assertTrue(personService.auth("neco@neco.cz", "heslo"));
    }
}
