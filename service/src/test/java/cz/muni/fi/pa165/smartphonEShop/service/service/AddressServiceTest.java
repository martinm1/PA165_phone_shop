package cz.muni.fi.pa165.smartphonEShop.service.service;


import cz.muni.fi.pa165.smartphonEShop.dao.AddressDao;
import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.enums.AddressEnum;
import cz.muni.fi.pa165.smartphonEShop.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Created by Roman Nahalka
 * Class represents: Tests for AddressService.
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class AddressServiceTest extends AbstractTestNGSpringContextTests
{
    @Mock
    private AddressDao addressDao;

    @Autowired
    @InjectMocks
    private AddressService addressService;

    private Address address1;
    private Address address2;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void testPrepare()
    {
        address1 = new Address();
        address2 = new Address();

        address1.setId(10L);
        address2.setId(20L);

        address1.setCity("Brno");
        address1.setCountry("Czech");

        address2.setCity("Bratislava");
        address2.setCity("Slovakia");
    }

    @Test
    public void getAllTest()
    {
        when(addressDao.findAll()).thenReturn(Stream.of(address1, address2).collect(Collectors.toList()));

        List<Address> addresses = addressService.getAllAddresses();

        Assert.assertEquals(2, addresses.size());
        Assert.assertTrue(addresses.contains(address1));
        Assert.assertTrue(addresses.contains(address2));
    }

    @Test
    public void findAddressById()
    {
        when(addressDao.findById(address1.getId())).thenReturn(address1);
        when(addressDao.findById(address2.getId())).thenReturn(address2);

        Address address = addressService.findAddressById(address1.getId());
        Address address3 = addressService.findAddressById(address2.getId());

        Assert.assertEquals(address, address1);
        Assert.assertEquals(address3, address2);
    }

    @Test
    public void createAddress()
    {
        Address address = new Address();

        doAnswer(invocationOnMock ->
        {
            address.setId(30L);
            return 30L;
        }).when(addressDao).create(address);

        addressService.createAddress(address);
        Assert.assertNotNull(address.getId());
        Assert.assertEquals(30L, address.getId().longValue());
    }

    @Test
    public void findAllAddressesBy()
    {
        HashMap<AddressEnum, String> hashMap = new HashMap<>();
        HashMap<AddressEnum, String> hashMap1 = new HashMap<>();

        hashMap.put(AddressEnum.CITY, "Brno");
        hashMap.put(AddressEnum.COUNTRY, "Czech");

        hashMap1.put(AddressEnum.CITY, "Bratislava");
        hashMap1.put(AddressEnum.COUNTRY, "Slovakia");

        when(addressDao.findAllAddressesBy(hashMap)).thenReturn(Stream.of(address1).collect(Collectors.toList()));
        when(addressDao.findAllAddressesBy(hashMap1)).thenReturn(Stream.of(address2).collect(Collectors.toList()));

        List<Address> addresses =  addressService.findAllAddressesBy(hashMap);
        List<Address> addresses1 = addressService.findAllAddressesBy(hashMap1);

        Assert.assertTrue(addresses.contains(address1));
        Assert.assertFalse(addresses.contains(address2));
        Assert.assertTrue(addresses1.contains(address2));
        Assert.assertFalse(addresses1.contains(address1));
    }
}
