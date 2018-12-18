package cz.muni.pa165.smartphonEShop.sampledata;

import com.sun.org.apache.xpath.internal.operations.Or;
import cz.muni.fi.pa165.smartphonEShop.entity.*;
import cz.muni.fi.pa165.smartphonEShop.enums.*;
import cz.muni.fi.pa165.smartphonEShop.service.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;

@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade
{
    @Autowired
    private AddressService addressService;

    @Autowired
    private ClaimService claimService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PersonService personService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private StockService stockService;

    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException
    {
        Address adminAddress = address("Bezrucova", "3", "Boskovice", "Czech Republic");
        Address customerAddress = address("Tridni", "5", "Brno", "Czech Republic");
        Address stockAddress = address("Botanicka", "1", "Brno", "Czech Republic");

        Person admin = person("Roman", "Nahalka", "heslo", adminAddress,
                "admin@admin.cz", "666999666", LocalDate.of(1994, 4, 5),
                Gender.MALE, PersonType.ADMIN);
        Person customer = person("David", "Novak", "heslo", customerAddress,
                "neco@seznam.cz", "333111222", LocalDate.of(1997, 7, 20),
                Gender.MALE, PersonType.SIGNED_USER);

        Stock stock = stock("Hlavni", stockAddress);

        Phone apple = phone("7", 17666, "predrazenej kram", Manufacturer.APPLE, stock);
        Phone huawei = phone("P20", 6666, "stenice z ciny", Manufacturer.HUAWEI, stock);

        Order order1 = order(OrderState.CREATED, LocalDate.of(2018, 11, 11), customer, apple);
        Order order2 = order(OrderState.FINISHED, LocalDate.of(2017, 5, 5), customer, huawei);

        Claim claim = claim(order2, ClaimSolution.REPAIR, "rozbitej", ClaimState.CREATED);
    }

    private Address address(String streetName, String streetNumber, String city, String country)
    {
        Address address = new Address();

        address.setStreetName(streetName);
        address.setStreetNumber(streetNumber);
        address.setCity(city);
        address.setCountry(country);

        addressService.createAddress(address);

        return address;
    }

    private Claim claim(Order order, ClaimSolution solution, String reason, ClaimState state)
    {
        Claim claim = new Claim();

        claim.setOrder(order);
        claim.setWantedSolutionByCustomer(solution);
        claim.setReasonOfClaim(reason);
        claim.setClaimState(state);

        claimService.createClaim(claim);

        return claim;
    }

    private Order order(OrderState state, LocalDate date, Person person, Phone phone)
    {
        Order order = new Order();

        order.setState(state);
        order.setOrderDate(date);
        order.setPerson(person);
        order.setPhone(phone);

        orderService.createOrder(order);

        return order;
    }

    private Person person(String firstName, String lastName, String password, Address address, String mail,
                          String phoneNumber, LocalDate dateOfBirth, Gender gender, PersonType personType)
    {
        Person person = new Person();

        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAddress(address);
        person.setEmail(mail);
        person.setPhoneNumber(phoneNumber);
        person.setDateOfBirth(dateOfBirth);
        person.setGender(gender);
        person.setPersonType(personType);

        personService.registerPerson(person, password);

        return person;
    }

    private Phone phone(String modelName, int price, String technicalInfo, Manufacturer manufacturer, Stock stock)
    {
        Phone phone = new Phone();

        phone.setModelName(modelName);
        phone.setPrice(price);
        phone.setTechnicalInfo(technicalInfo);
        phone.setManufacturer(manufacturer);
        phone.setStock(stock);

        phoneService.createPhone(phone);

        return phone;
    }

    private Stock stock(String name, Address address)
    {
        Stock stock = new Stock();

        stock.setName(name);
        stock.setAddress(address);

        stockService.createStock(stock);

        return stock;
    }
}
