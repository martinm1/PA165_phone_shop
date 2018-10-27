package cz.muni.fi.pa165.smartphonEShop.dao;

import cz.muni.fi.pa165.smartphonEShop.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.smartphonEShop.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.smartphonEShop.enums.Gender;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import java.time.LocalDate;
import java.util.List;
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

    @Test
    public void findAll()
    {
        Person person1 = new Person();
        Person person2 = new Person();

        person1.setFirstName("Franta");
        person2.setFirstName("Jarda");

        person1.setLastName("Novák");
        person2.setLastName("Dvořák");
        
        person1.setAddressId(123L);
        person2.setAddressId(456L);
        
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
        
        person.create(person1);
        person.create(person2);

        List<Person> addresses = person.findAll();
        Assert.assertEquals(addresses.size(), 2);
        Assert.assertTrue(addresses.contains(person1));
        Assert.assertTrue(addresses.contains(person2));
    }

    @Test
    public void findById()
    {
        Person person1 = new Person();
        Person person2 = new Person();

        person1.setFirstName("Franta");
        person2.setFirstName("Jarda");

        person1.setLastName("Novák");
        person2.setLastName("Dvořák");
        
        person1.setAddressId(123L);
        person2.setAddressId(456L);
        
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
        
        person.create(person1);
        person.create(person2);

        Assert.assertEquals("123", person.findById(person1.getId()).getPhoneNumber());
        Assert.assertEquals("456", person.findById(person2.getId()).getPhoneNumber());

    }

    @Test
    public void delete()
    {
        Person person1 = new Person();
        Person person2 = new Person();

        person1.setFirstName("Franta");
        person2.setFirstName("Jarda");

        person1.setLastName("Novák");
        person2.setLastName("Dvořák");
        
        person1.setAddressId(123L);
        person2.setAddressId(456L);
        
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
        
        person.create(person1);
        person.create(person2);

        Assert.assertEquals(person.findAll(), 2);
        Assert.assertNotNull(person.findById(person1.getId()));
        Assert.assertNotNull(person.findById(person2.getId()));

        person.delete(person1);
        Assert.assertEquals(person.findAll(), 1);
        Assert.assertFalse(person.findAll().contains(person1));
        Assert.assertNull(person.findById(person1.getId()));

        person.delete(person2);
        Assert.assertEquals(person.findAll(), 0);
        Assert.assertNull(person.findById(person2.getId()));
    }

    @Test
    public void update()
    {
        Person person1 = new Person();

        person1.setFirstName("Franta");

        person1.setLastName("Novák");
        
        person1.setAddressId(123L);
        
        person1.setEmail("zadny-takovyhle-novak-neexistuje12345@seznam.cz");
         
        person1.setPhoneNumber("123");
        
        person1.setDateOfBirth(LocalDate.ofYearDay(1, 2));
        
        person1.setGender(Gender.MALE);
        
        person1.setPersonType(PersonType.ADMIN);
        
        person.create(person1);

        person1.setEmail("zadny-takovyhle-novak-neexistuje12345@centrum.cz");
        person.update(person1);

        Assert.assertEquals("zadny-takovyhle-novak-neexistuje12345@centrum.cz", person.findById(person1.getId()).getEmail());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNull()
    {
        person.create(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNull()
    {
        person.update(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNull()
    {
        person.delete(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNull()
    {
        person.findById(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNegative()
    {
        person.findById((long)-1);
    }
}
