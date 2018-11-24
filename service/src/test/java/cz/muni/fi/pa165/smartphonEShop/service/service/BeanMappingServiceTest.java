package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.dto.*;
import cz.muni.fi.pa165.smartphonEShop.entity.*;
import cz.muni.fi.pa165.smartphonEShop.enums.*;
import cz.muni.fi.pa165.smartphonEShop.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class BeanMappingServiceTest extends AbstractTestNGSpringContextTests
{
    @Autowired
    BeanMappingService bms;

    private List<Address> addresses = new ArrayList<>();
    private List<Claim> claims = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private List<Person> people = new ArrayList<>();
    private List<Phone> phones = new ArrayList<>();
    private List<Stock> stocks = new ArrayList<>();

    @BeforeMethod
    public void testPrepare()
    {
        //Clean list
        addresses.clear();
        claims.clear();
        orders.clear();
        people.clear();
        phones.clear();
        stocks.clear();

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

        addresses.add(address1);
        addresses.add(address2);

        Person person1 = new Person();
        person1.setFirstName("Franta");
        person1.setLastName("Novák");
        person1.setAddress(address1);
        person1.setEmail("zadny-takovyhle-novak-neexistuje12345@seznam.cz");
        person1.setPhoneNumber("123");
        person1.setDateOfBirth(LocalDate.ofYearDay(1, 2));
        person1.setGender(Gender.MALE);
        person1.setPersonType(PersonType.ADMIN);

        Person person2 = new Person();
        person2.setFirstName("Franta");
        person2.setLastName("Novák");
        person2.setAddress(address1);
        person2.setEmail("zadny-takovyhle-novak-neexistuje12345@seznam.cz");
        person2.setPhoneNumber("123");
        person2.setDateOfBirth(LocalDate.ofYearDay(1, 2));
        person2.setGender(Gender.MALE);
        person2.setPersonType(PersonType.ADMIN);

        people.add(person1);
        people.add(person2);

        Stock stock = new Stock();
        stock.setName("tovaren");
        stock.setAddress(address2);

        stocks.add(stock);

        Phone phone1 = new Phone();
        phone1.setId(11L);
        phone1.setModelName("S6");
        phone1.setPrice(123);
        phone1.setTechnicalInfo("info1");
        phone1.setManufacturer(Manufacturer.APPLE);
        phone1.setStock(stock);

        Phone phone2 = new Phone();
        phone2.setId(21L);
        phone2.setModelName("S6");
        phone2.setPrice(123);
        phone2.setTechnicalInfo("info1");
        phone2.setManufacturer(Manufacturer.APPLE);
        phone2.setStock(stock);

        phones.add(phone1);
        phones.add(phone2);

        stock.setPhones(phones);

        Order order1 = new Order();
        order1.setOrderDate(LocalDate.ofYearDay(2018, 10));
        order1.setPerson(person1);
        order1.setPhone(phone1);
        order1.setState(OrderState.CREATED);

        Order order2 = new Order();
        order2.setOrderDate(LocalDate.ofYearDay(2018,10));
        order2.setPerson(person2);
        order2.setPhone(phone2);
        order2.setState(OrderState.CREATED);

        orders.add(order1);
        orders.add(order2);

        Claim claim1 = new Claim();
        claim1.setOrder(order1);
        claim1.setClaimState(ClaimState.ACCEPTED);
        claim1.setReasonOfClaim("rozbity");
        claim1.setWantedSolutionByCustomer(ClaimSolution.REPAIR);
        claim1.setTechnicalReport("chyba");

        Claim claim2 = new Claim();
        claim2.setOrder(order2);
        claim2.setClaimState(ClaimState.CREATED);
        claim2.setReasonOfClaim("rozbity2");
        claim2.setWantedSolutionByCustomer(ClaimSolution.MONEY);
        claim2.setTechnicalReport("vratene peniaze");

        claims.add(claim1);
        claims.add(claim2);
    }

    @Test
    public void mapAddresses()
    {
        Collection<AddressDTO> adtos = bms.mapTo(addresses, AddressDTO.class);

        Assert.assertEquals(adtos.size(), 2);
    }

    @Test
    public void mapStocks()
    {
        Collection<StockDTO> stdtos = bms.mapTo(stocks, StockDTO.class);

        Assert.assertEquals(stdtos.size(), 1);
    }

    @Test
    public void mapOrders()
    {
        Collection<OrderDTO> ordtos = bms.mapTo(orders, OrderDTO.class);

        Assert.assertEquals(ordtos.size(), 2);
    }

    @Test
    public void mapClaims()
    {
        Collection<ClaimDTO> cldtos = bms.mapTo(claims, ClaimDTO.class);

        Assert.assertEquals(cldtos.size(), 2);
    }

    @Test
    public void mapPeople()
    {
        Collection<PersonDTO> pedtos = bms.mapTo(people, PersonDTO.class);

        Assert.assertEquals(pedtos.size(), 2);
    }

    @Test
    public void mapPhones()
    {
        Collection<PhoneDTO> phdtos = bms.mapTo(phones, PhoneDTO.class);

        Assert.assertEquals(phdtos.size(), 2);
    }

    @Test
    public void mapInnerPhones()
    {
        List<StockDTO> stdtos = bms.mapTo(stocks, StockDTO.class);
        Collection<PhoneDTO> phdtos = bms.mapTo(stdtos.get(0).getPhones(), PhoneDTO.class);

        Assert.assertEquals(phdtos.size(), 2);
    }
}
