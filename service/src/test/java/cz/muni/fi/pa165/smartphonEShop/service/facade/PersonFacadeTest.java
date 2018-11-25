package cz.muni.fi.pa165.smartphonEShop.service.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.AddressDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.OrderDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.PersonDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.entity.Order;
import cz.muni.fi.pa165.smartphonEShop.entity.Person;
import cz.muni.fi.pa165.smartphonEShop.enums.Gender;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
import cz.muni.fi.pa165.smartphonEShop.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.smartphonEShop.service.service.BeanMappingService;
import cz.muni.fi.pa165.smartphonEShop.service.service.PersonService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author martin
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PersonFacadeTest {
    @Mock
    private PersonService personService;
    
    @Mock
    private BeanMappingService bms;
    
    @InjectMocks
    private PersonFacadeImpl personFacade;
    
    private Person person1;
    private Person person2;

    private PersonDTO personDTO1;
    private PersonDTO personDTO2;
    
    private Address address1;
    private Address address2;
    
    private AddressDTO addressDTO1;
    private AddressDTO addressDTO2;
    
    private Order order2;
    
    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void prepare(){
        person1 = new Person();
        person2 = new Person();
        
        personDTO1 = new PersonDTO();
        personDTO2 = new PersonDTO();
        
        person1.setFirstName("Franta");
        person2.setFirstName("Jarda");
        
        personDTO1.setFirstName("Franta");
        personDTO2.setFirstName("Jarda");
        
        person1.setLastName("Novák");
        person2.setLastName("Dvořák");
        
        personDTO1.setLastName("Novák");
        personDTO2.setLastName("Dvořák");
        
        person1.setEmail("zadny-takovyhle-novak-neexistuje12345@seznam.cz");
        person2.setEmail("zadny-takovyhle-dvorak-neexistuje12345@seznam.cz");
        
        personDTO1.setEmail("zadny-takovyhle-novak-neexistuje12345@seznam.cz");
        personDTO2.setEmail("zadny-takovyhle-dvorak-neexistuje12345@seznam.cz");
         
        person1.setPhoneNumber("123");
        person2.setPhoneNumber("456");
        
        personDTO1.setPhoneNumber("123");
        personDTO2.setPhoneNumber("456");
        
        person1.setDateOfBirth(LocalDate.ofYearDay(1, 2));
        person2.setDateOfBirth(LocalDate.ofYearDay(3, 4));
        
        personDTO1.setDateOfBirth(LocalDate.ofYearDay(1, 2));
        personDTO2.setDateOfBirth(LocalDate.ofYearDay(3, 4));
        
        person1.setGender(Gender.MALE);
        person2.setGender(Gender.FEMALE);
        
        personDTO1.setGender(Gender.MALE);
        personDTO2.setGender(Gender.FEMALE);
        
        person1.setPersonType(PersonType.ADMIN);
        person2.setPersonType(PersonType.SIGNED_USER);
        
        personDTO1.setPersonType(PersonType.ADMIN);
        personDTO2.setPersonType(PersonType.SIGNED_USER);
        
        person1.setId(10L);
        person1.setId(20L);
        
        personDTO1.setId(10L);
        personDTO2.setId(20L);
        
        address1 = new Address();
        address2 = new Address();

        addressDTO1 = new AddressDTO();
        addressDTO2 = new AddressDTO();
        
        address1.setStreetName("Botanická");
        address2.setStreetName("Ilkovičova");
        
        addressDTO1.setStreetName("Botanická");
        addressDTO2.setStreetName("Ilkovičova");

        address1.setStreetNumber("68A");
        address2.setStreetNumber("2");
        
        addressDTO1.setStreetNumber("68A");
        addressDTO2.setStreetNumber("2");
        
        address1.setCity("Brno");
        address2.setCity("Bratislava");
        
        addressDTO1.setCity("Brno");
        addressDTO2.setCity("Bratislava");
        
        address1.setCountry("Česko");
        address2.setCountry("Slovensko");
        
        addressDTO1.setCountry("Česko");
        addressDTO2.setCountry("Slovensko");
        
        address1.setId(30L);
        address2.setId(40L);
        
        addressDTO1.setId(30L);
        addressDTO2.setId(40L);
        
        person1.setAddress(address1);
        person2.setAddress(address2);
        
        personDTO1.setAddress(addressDTO1);
        personDTO2.setAddress(addressDTO2);
        
        List<Order> orders1 = new ArrayList();
        person1.setOrders(orders1);
        
        List<Order> orders2 = new ArrayList();
        person2.setOrders(orders2);
        
        List<OrderDTO> orderDTOs1 = new ArrayList();
        personDTO1.setOrders(orderDTOs1);
        
        List<OrderDTO> orderDTOs2 = new ArrayList();
        personDTO2.setOrders(orderDTOs2);
        
        order2 = new Order();
        order2.setId(5L);
       
        person2.addOrder(order2);
    }
    
    
    @Test
    public void findPersonById(){
        when(personService.findPersonById(10L)).thenReturn(person1);
        when(personService.findPersonById(20L)).thenReturn(person2);
        when(bms.mapTo(person1, PersonDTO.class)).thenReturn(personDTO1);
        when(bms.mapTo(person2, PersonDTO.class)).thenReturn(personDTO2);
        
        Assert.assertEquals(personDTO1, personFacade.findPersonById(10L));
        Assert.assertEquals(personDTO2, personFacade.findPersonById(20L));
    }
    
    @Test
    public void findPersonByEmail(){
        when(personService.findPersonByEmail("zadny-takovyhle-novak-neexistuje12345@seznam.cz")).thenReturn(person1);
        when(personService.findPersonByEmail("zadny-takovyhle-dvorak-neexistuje12345@seznam.cz")).thenReturn(person2);
        when(bms.mapTo(person1, PersonDTO.class)).thenReturn(personDTO1);
        when(bms.mapTo(person2, PersonDTO.class)).thenReturn(personDTO2);
        
        Assert.assertEquals(personDTO1, personFacade.findPersonByEmail("zadny-takovyhle-novak-neexistuje12345@seznam.cz"));
        Assert.assertEquals(personDTO2, personFacade.findPersonByEmail("zadny-takovyhle-dvorak-neexistuje12345@seznam.cz"));
    }
    
    @Test
    public void findPersonByPhoneNumber(){
        when(personService.findPersonByPhoneNumber("123")).thenReturn(person1);
        when(personService.findPersonByPhoneNumber("456")).thenReturn(person2);
        when(bms.mapTo(person1, PersonDTO.class)).thenReturn(personDTO1);
        when(bms.mapTo(person2, PersonDTO.class)).thenReturn(personDTO2);
        
        Assert.assertEquals(personDTO1, personFacade.findPersonByPhoneNumber("123"));
        Assert.assertEquals(personDTO2, personFacade.findPersonByPhoneNumber("456"));
    }
    
    @Test
    public void getAllPeople(){
        List<Person> retPeople = Arrays.asList(person1, person2);
        List<PersonDTO> retDTOs = Arrays.asList(personDTO1, personDTO2);

        when(personService.getAllPeople()).thenReturn(retPeople);
        when(bms.mapTo(retPeople, PersonDTO.class)).thenReturn(retDTOs);

        Collection<PersonDTO> people = personFacade.getAllPeople();

        Assert.assertEquals(2, people.size());
    }
    
    @Test
    public void getPeopleByPersonType(){
        when(personService.getPeopleByPersonType(PersonType.ADMIN)).thenReturn(Collections.singletonList(person1));
        when(personService.getPeopleByPersonType(PersonType.SIGNED_USER)).thenReturn(Collections.singletonList(person2));
        when(bms.mapTo(Collections.singletonList(person1), PersonDTO.class)).thenReturn(Collections.singletonList(personDTO1));
        when(bms.mapTo(Collections.singletonList(person2), PersonDTO.class)).thenReturn(Collections.singletonList(personDTO2));

        Collection<PersonDTO> people = personFacade.getPeopleByPersonType(PersonType.ADMIN);

        Assert.assertEquals(people.size(), 1);
        Assert.assertTrue(people.contains(personDTO1));

        people = personFacade.getPeopleByPersonType(PersonType.SIGNED_USER);

        Assert.assertEquals(people.size(), 1);
        Assert.assertTrue(people.contains(personDTO2));
    }
    
    @Test
    public void addOrder()
    {
        Order orderTest = new Order();
        orderTest.setId(7L);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(7L);

        doAnswer(invocationOnMock ->
        {
            person1.addOrder(orderTest);
            return 7L;
        }).when(personService).addOrder(10L, 7L);

        doAnswer(invocationOnMock ->
        {
            personDTO1.setOrders(Collections.singletonList(orderDTO));
            return 7L;
        }).when(bms).mapTo(person1, PersonDTO.class);

        
        when(personService.findPersonById(10L)).thenReturn(person1);
        when(bms.mapTo(person1, PersonDTO.class)).thenReturn(personDTO1);
        
        Assert.assertTrue(personService.findPersonById(10L).getOrders().isEmpty());
        
        personFacade.addOrder(10L, 7L);
        
        Assert.assertTrue(personFacade.findPersonById(10L).getOrders().contains(orderDTO));
        Assert.assertTrue(personService.findPersonById(10L).getOrders().contains(orderTest));
    }
    
    @Test
    public void removeOrder()
    {
        OrderDTO orderDTO2 = new OrderDTO();
        orderDTO2.setId(5L);
        
        doAnswer(invocationOnMock ->
        {
            person2.removeOrder(order2);
            return 5L;
        }).when(personService).removeOrder(20L, 5L);

        when(personService.findPersonById(20L)).thenReturn(person2);
        when(bms.mapTo(person2, PersonDTO.class)).thenReturn(personDTO2);
        
        when(bms.mapTo(order2, OrderDTO.class)).thenReturn(orderDTO2);

        bms.mapTo(order2, OrderDTO.class);
        
        Assert.assertTrue(personService.findPersonById(20L).getOrders().contains(order2));

        personFacade.removeOrder(20L, 5L);
        
        Assert.assertTrue(personFacade.findPersonById(20L).getOrders().isEmpty());
        Assert.assertTrue(personService.findPersonById(20L).getOrders().isEmpty());
    }
    
    @Test
    public void registerPerson()
    {    
        Person person3 = new Person();
        person3.setAddress(address1);
        person3.setDateOfBirth(LocalDate.MIN);
        person3.setEmail("");
        person3.setFirstName("");
        person3.setGender(Gender.MALE);
        person3.setId(Long.MIN_VALUE);
        person3.setLastName("");
        person3.setPersonType(PersonType.EMPLOYEE);
        person3.setPhoneNumber("");
        
        PersonDTO personDTO3 = new PersonDTO();
        personDTO3.setAddress(addressDTO1);
        personDTO3.setDateOfBirth(LocalDate.MIN);
        personDTO3.setEmail("");
        personDTO3.setFirstName("");
        personDTO3.setGender(Gender.MALE);
        personDTO3.setId(30L);
        personDTO3.setLastName("");
        personDTO3.setPersonType(PersonType.EMPLOYEE);
        personDTO3.setPhoneNumber("");
        
        
        PersonDTO personDTO = bms.mapTo(person3, PersonDTO.class);
        personFacade.registerPerson(personDTO3);
        verify(personService).registerPerson(any(Person.class));
    }
}
