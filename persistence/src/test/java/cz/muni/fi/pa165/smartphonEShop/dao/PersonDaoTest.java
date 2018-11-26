package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Person;
import cz.muni.fi.pa165.smartphonEShop.exceptions.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.smartphonEShop.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.enums.Gender;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import java.time.LocalDate;
import java.util.List;
import org.testng.annotations.BeforeMethod;
/**
 *
 * @author martin
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PersonDaoTest extends  AbstractTestNGSpringContextTests{
    @Autowired
    private PersonDao person;
    
    @Autowired
    private AddressDao address;

    private Person person1;
    private Person person2;
    
    private Address address1;
    private Address address2;
    
    @BeforeMethod
    public void setUp(){
        person1 = new Person();
        person2 = new Person();

        person1.setFirstName("Franta");
        person2.setFirstName("Jarda");

        person1.setLastName("Novák");
        person2.setLastName("Dvořák");
        
       // person1.setAddressId(123L);
      //  person2.setAddressId(456L);
        
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
        
        
        address1 = new Address();
        address2 = new Address();

        address1.setStreetName("Botanická");
        address2.setStreetName("Ilkovičova");

        address1.setStreetNumber("68A");
        address2.setStreetNumber("2");
        
        address1.setCity("Brno");
        address2.setCity("Bratislava");
        
        address1.setCountry("Česko");
        address2.setCountry("Slovensko");
        
        address.create(address1);
        address.create(address2);
        
        person1.setAddress(address1);
        person2.setAddress(address2);
    
    }
    
    @Test
    public void create() {

        List<Person> persons = person.findAll();
        Assert.assertEquals(persons.size(), 0);

        person.create(person1);
        persons = person.findAll();
        Assert.assertEquals(persons.size(), 1);

        person.create(person2);
        persons = person.findAll();
        Assert.assertEquals(persons.size(), 2);
    }
    
    @Test
    public void findAll()
    {
        person.create(person1);
        person.create(person2);

        List<Person> persons = person.findAll();
        Assert.assertEquals(persons.size(), 2);
        Assert.assertTrue(persons.contains(person1));
        Assert.assertTrue(persons.contains(person2));
    }

    @Test
    public void findById()
    {
        person.create(person1);
        person.create(person2);

        Assert.assertEquals(person1, person.findById(person1.getId()));
        Assert.assertEquals(person2, person.findById(person2.getId()));

    }
    
    @Test
    public void findByEmail()
    {
        person.create(person1);
        person.create(person2);

        Assert.assertEquals(person1, person.findByEmail(person1.getEmail()));
        Assert.assertEquals(person2, person.findByEmail(person2.getEmail()));

    }
    
    @Test
    public void findByPhoneNumber()
    {
        person.create(person1);
        person.create(person2);

        Assert.assertEquals(person1, person.findByPhoneNumber(person1.getPhoneNumber()));
        Assert.assertEquals(person2, person.findByPhoneNumber(person2.getPhoneNumber()));

    }
    
    @Test
    public void findByPersonType()
    {
        person.create(person1);
        person.create(person2);

        Assert.assertTrue(person.findByPersonType(person1.getPersonType()).contains(person1));
        Assert.assertTrue(person.findByPersonType(person2.getPersonType()).contains(person2));

    }

    @Test
    public void delete()
    {
        person.create(person1);
        person.create(person2);

        Assert.assertEquals(person.findAll().size(), 2);
        Assert.assertNotNull(person.findById(person1.getId()));
        Assert.assertNotNull(person.findById(person2.getId()));

        person.delete(person1);
        Assert.assertEquals(person.findAll().size(), 1);
        Assert.assertFalse(person.findAll().contains(person1));
        Assert.assertNull(person.findById(person1.getId()));

        person.delete(person2);
        Assert.assertEquals(person.findAll().size(), 0);
        Assert.assertNull(person.findById(person2.getId()));
    }

    @Test
    public void update()
    {
        person.create(person1);

        person1.setEmail("zadny-takovyhle-novak-neexistuje12345@centrum.cz");
        person.update(person1);
        
        Assert.assertEquals("zadny-takovyhle-novak-neexistuje12345@centrum.cz", person.findById(person1.getId()).getEmail());
        Assert.assertEquals(person1, person.findById(person1.getId()));
    }

    @Test(expectedExceptions = DAOException.class)
    public void createNull()
    {
        person.create(null);
    }

    @Test(expectedExceptions = DAOException.class)
    public void updateNull()
    {
        person.update(null);
    }

    @Test(expectedExceptions = DAOException.class)
    public void deleteNull()
    {
        person.delete(null);
    }

    @Test(expectedExceptions = DAOException.class)
    public void findByIdNull()
    {
        person.findById(null);
    }

    @Test(expectedExceptions = DAOException.class)
    public void findByIdNegative()
    {
        person.findById((long)-1);
    }
}
