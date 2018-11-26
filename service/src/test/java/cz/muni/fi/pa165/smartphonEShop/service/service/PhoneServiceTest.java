package cz.muni.fi.pa165.smartphonEShop.service.service;

import cz.muni.fi.pa165.smartphonEShop.dao.PhoneDao;
import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Created by Jakub Ondrusek
 * Class represents: Tests for PhoneService.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PhoneServiceTest  extends AbstractTestNGSpringContextTests {

    @Mock
    private PhoneDao phoneDao;

    @Autowired
    @InjectMocks
    private PhoneService phoneService;

    private Phone phone1;
    private Phone phone2;
    private Phone phone3;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void testPrepare()
    {
        phone1 = new Phone();
        phone2 = new Phone();
        phone3 = new Phone();

        phone1.setId(1L);
        phone2.setId(2L);
        phone3.setId(3L);
    }
    @Test
    public void findAllPhones()
    {
        List<Phone> ret = Arrays.asList(phone1, phone2, phone3);
        when(phoneDao.findAll()).thenReturn(ret);

        List<Phone> phones = phoneService.findAllPhones();

        Assert.assertEquals(3, phones.size());
        Assert.assertTrue(phones.contains(phone1));
        Assert.assertTrue(phones.contains(phone2));
        Assert.assertTrue(phones.contains(phone3));
    }

    @Test
    public void findPhoneById()
    {
        when(phoneDao.findById(phone1.getId())).thenReturn(phone1);
        when(phoneDao.findById(phone2.getId())).thenReturn(phone2);
        when(phoneDao.findById(phone3.getId())).thenReturn(phone3);

        Phone testPhone1 = phoneService.findPhoneById(phone1.getId());
        Phone testPhone2 = phoneService.findPhoneById(phone2.getId());
        Phone testPhone3 = phoneService.findPhoneById(phone3.getId());

        Assert.assertEquals(testPhone1, phone1);
        Assert.assertEquals(testPhone2, phone2);
        Assert.assertEquals(testPhone3, phone3);
    }

    @Test
    public void createPhone()
    {
        Phone phone = new Phone();

        doAnswer(invocationOnMock ->
        {
            phone.setId(4L);
            return 4L;
        }).when(phoneDao).create(phone);

        phoneService.createPhone(phone);
        Assert.assertNotNull(phone.getId());
        Assert.assertEquals(4L, phone.getId().longValue());
    }

    @Test
    public void findPhonesByModelName()
    {
        List<Phone> ret = Arrays.asList(phone2, phone3);
        when(phoneDao.findPhonesByModelName("nokia")).thenReturn(Collections.singletonList(phone1));
        when(phoneDao.findPhonesByModelName("iphone")).thenReturn(ret);

        List<Phone> phones = phoneService.findPhonesByModelName("nokia");

        Assert.assertEquals(1, phones.size());
        Assert.assertTrue(phones.contains(phone1));

        phones = phoneService.findPhonesByModelName("iphone");

        Assert.assertEquals(2, phones.size());
        Assert.assertTrue(phones.contains(phone2));
        Assert.assertTrue(phones.contains(phone3));
    }

    @Test
    public void findPhonesByPrice()
    {
        List<Phone> ret = Arrays.asList(phone2, phone3);
        when(phoneDao.findPhonesByPrice(100)).thenReturn(Collections.singletonList(phone1));
        when(phoneDao.findPhonesByPrice(1000)).thenReturn(ret);

        List<Phone> phones = phoneService.findPhonesByPrice(100);

        Assert.assertEquals(1, phones.size());
        Assert.assertTrue(phones.contains(phone1));

        phones = phoneService.findPhonesByPrice(1000);

        Assert.assertEquals(2, phones.size());
        Assert.assertTrue(phones.contains(phone2));
        Assert.assertTrue(phones.contains(phone3));
    }
    @Test
    public void findPhonesByTechnicalInfo()
    {
        List<Phone> ret = Arrays.asList(phone2, phone3);
        when(phoneDao.findPhonesByTechnicalInfo("good phones")).thenReturn(Collections.singletonList(phone1));
        when(phoneDao.findPhonesByTechnicalInfo("bad phones")).thenReturn(ret);

        List<Phone> phones = phoneService.findPhonesByTechnicalInfo("good phones");

        Assert.assertEquals(1, phones.size());
        Assert.assertTrue(phones.contains(phone1));

        phones = phoneService.findPhonesByTechnicalInfo("bad phones");

        Assert.assertEquals(2, phones.size());
        Assert.assertTrue(phones.contains(phone2));
        Assert.assertTrue(phones.contains(phone3));
    }

    @Test
    public void findPhonesByManufacturer()
    {
        List<Phone> ret = Arrays.asList(phone2, phone3);
        when(phoneDao.findPhonesByManufacturer(Manufacturer.APPLE)).thenReturn(Collections.singletonList(phone1));
        when(phoneDao.findPhonesByManufacturer(Manufacturer.HUEAWEI)).thenReturn(ret);

        List<Phone> phones = phoneService.findPhonesByManufacturer(Manufacturer.APPLE);

        Assert.assertEquals(1, phones.size());
        Assert.assertTrue(phones.contains(phone1));

        phones = phoneService.findPhonesByManufacturer(Manufacturer.HUEAWEI);

        Assert.assertEquals(2, phones.size());
        Assert.assertTrue(phones.contains(phone2));
        Assert.assertTrue(phones.contains(phone3));
    }

    @Test
    public void findPhonesByStock()
    {
        List<Phone> ret = Arrays.asList(phone2, phone3);
        when(phoneDao.findPhonesByStock(1L)).thenReturn(Collections.singletonList(phone1));
        when(phoneDao.findPhonesByStock(2L)).thenReturn(ret);

        List<Phone> phones = phoneService.findPhonesByStock(1L);

        Assert.assertEquals(1, phones.size());
        Assert.assertTrue(phones.contains(phone1));

        phones = phoneService.findPhonesByStock(2L);

        Assert.assertEquals(2, phones.size());
        Assert.assertTrue(phones.contains(phone2));
        Assert.assertTrue(phones.contains(phone3));
    }


}
