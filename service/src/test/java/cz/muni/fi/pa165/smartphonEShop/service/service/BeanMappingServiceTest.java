package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.dto.AddressDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.OrderDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.StockDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.*;
import cz.muni.fi.pa165.smartphonEShop.enums.Gender;
import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import cz.muni.fi.pa165.smartphonEShop.enums.OrderState;
import cz.muni.fi.pa165.smartphonEShop.enums.PersonType;
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

        Person person = new Person();
        person.setFirstName("Franta");
        person.setLastName("Novák");
        person.setAddress(address1);
        person.setEmail("zadny-takovyhle-novak-neexistuje12345@seznam.cz");
        person.setPhoneNumber("123");
        person.setDateOfBirth(LocalDate.ofYearDay(1, 2));
        person.setGender(Gender.MALE);
        person.setPersonType(PersonType.ADMIN);

        Stock stock = new Stock();
        stock.setName("tovaren");
        stock.setAddress(address2);

        stocks.add(stock);

        Phone phone = new Phone();
        phone.setId(11L);
        phone.setModelName("S6");
        phone.setPrice(123);
        phone.setTechnicalInfo("info1");
        phone.setManufacturer(Manufacturer.APPLE);
        phone.setStock(stock);

        Phone phone2 = new Phone();
        phone.setId(21L);
        phone2.setModelName("S6");
        phone2.setPrice(123);
        phone2.setTechnicalInfo("info1");
        phone2.setManufacturer(Manufacturer.APPLE);
        phone2.setStock(stock);

        phones.add(phone);
        phones.add(phone2);

        stock.setPhones(phones);

        Order order1 = new Order();
        order1.setOrderDate(LocalDate.ofYearDay(2018, 10));
        order1.setPerson(person);
        order1.setPhone(phone);
        order1.setState(OrderState.CREATED);

        Order order2 = new Order();
        order2.setOrderDate(LocalDate.ofYearDay(2018,10));
        order2.setPerson(person);
        order2.setPhone(phone);
        order2.setState(OrderState.CREATED);

        orders.add(order1);
        orders.add(order2);
    }

    @Test
    public void shouldMapAddresses()
    {
        Collection<AddressDTO> adtos = bms.mapTo(addresses, AddressDTO.class);

        Assert.assertEquals(adtos.size(), 2);
    }

    @Test
    public void shouldMapStocks()
    {
        Collection<StockDTO> stdtos = bms.mapTo(stocks, StockDTO.class);

        Assert.assertEquals(stdtos.size(), 1);
    }

    @Test
    public void shouldMapOrders()
    {
        Collection<OrderDTO> ordtos = bms.mapTo(orders, OrderDTO.class);

        Assert.assertEquals(ordtos.size(), 2);
    }
}
