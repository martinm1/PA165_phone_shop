package cz.muni.fi.pa165.smartphonEShop.service.service;


import cz.muni.fi.pa165.smartphonEShop.dao.AddressDao;
import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

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

        address1.setCity("Brno");
        address1.setCountry("Czech");

        address2.setCity("Bratislava");
        address2.setCity("Slovakia");
    }

    @Test
    public void getAllTest()
    {
        List<Address> = addressService.getAllAdresses();
    }
}
