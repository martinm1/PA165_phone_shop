package cz.muni.fi.pa165.smartphonEShop.service.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.PhoneDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Phone;
import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import cz.muni.fi.pa165.smartphonEShop.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.smartphonEShop.service.service.BeanMappingService;
import cz.muni.fi.pa165.smartphonEShop.service.service.PhoneService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Created by Jakub Ondrusek
 * Class represents: Tests for phoneFacade class
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PhoneFacadeTest extends AbstractTestNGSpringContextTests {
    @Mock
    private PhoneService phoneService;

    @Mock
    private BeanMappingService bms;

    @InjectMocks
    private PhoneFacadeImpl phoneFacade;

    private Phone phone1;
    private Phone phone2;

    private PhoneDTO phoneDTO1;
    private PhoneDTO phoneDTO2;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void testPrepare() {
        phone1 = new Phone();
        phone2 = new Phone();

        phoneDTO1 = new PhoneDTO();
        phoneDTO2 = new PhoneDTO();
    }

    @Test
    public void getAllPhones() {
        List<Phone> retPhones = Arrays.asList(phone1, phone2);

        when(phoneService.findAllPhones()).thenReturn(retPhones);
        when(bms.mapTo(retPhones, PhoneDTO.class)).thenReturn(Arrays.asList(phoneDTO1,phoneDTO2));

        Collection<PhoneDTO> phones = phoneFacade.getAllPhones();
        Assert.assertEquals(2,phones.size());
    }

    @Test
    public void findPhoneById() {
        when(phoneService.findPhoneById(1L)).thenReturn(phone1);
        when(phoneService.findPhoneById(2L)).thenReturn(phone2);
        when(bms.mapTo(phone1, PhoneDTO.class)).thenReturn(phoneDTO1);
        when(bms.mapTo(phone2, PhoneDTO.class)).thenReturn(phoneDTO2);

        Assert.assertEquals(phoneDTO1, phoneFacade.findPhoneById(1L));
        Assert.assertEquals(phoneDTO2, phoneFacade.findPhoneById(2L));
    }

    @Test
    public void findPhonesByModelName() {
        when(phoneService.findPhonesByModelName("nokia")).thenReturn(Collections.singletonList(phone1));
        when(phoneService.findPhonesByModelName("ihpone")).thenReturn(Collections.singletonList(phone2));

        when(bms.mapTo(Collections.singletonList(phone1), PhoneDTO.class)).thenReturn(Collections.singletonList(phoneDTO1));
        when(bms.mapTo(Collections.singletonList(phone2), PhoneDTO.class)).thenReturn(Collections.singletonList(phoneDTO2));

        Collection<PhoneDTO> foundPhones = phoneFacade.findPhonesByModelName("nokia");
        Assert.assertEquals(1,foundPhones.size());
        Assert.assertTrue(foundPhones.contains(phoneDTO1));

        foundPhones = phoneFacade.findPhonesByModelName("ihpone");
        Assert.assertEquals(1,foundPhones.size());
        Assert.assertTrue(foundPhones.contains(phoneDTO2));
    }
    @Test
    public void findPhonesByPrice() {
        when(phoneService.findPhonesByPrice(99, 101)).thenReturn(Collections.singletonList(phone1));
        when(phoneService.findPhonesByPrice(999,1001)).thenReturn(Collections.singletonList(phone2));

        when(bms.mapTo(Collections.singletonList(phone1), PhoneDTO.class)).thenReturn(Collections.singletonList(phoneDTO1));
        when(bms.mapTo(Collections.singletonList(phone2), PhoneDTO.class)).thenReturn(Collections.singletonList(phoneDTO2));

        Collection<PhoneDTO> foundPhones = phoneFacade.findPhonesByPriceInterval(99, 101);
        Assert.assertEquals(1,foundPhones.size());
        Assert.assertTrue(foundPhones.contains(phoneDTO1));

        foundPhones = phoneFacade.findPhonesByPriceInterval(999,1001);
        Assert.assertEquals(1,foundPhones.size());
        Assert.assertTrue(foundPhones.contains(phoneDTO2));
    }
    @Test
    public void findPhonesByTechnicalInfo() {
        when(phoneService.findPhonesByTechnicalInfo("good phone")).thenReturn(Collections.singletonList(phone1));
        when(phoneService.findPhonesByTechnicalInfo("bad phone")).thenReturn(Collections.singletonList(phone2));

        when(bms.mapTo(Collections.singletonList(phone1), PhoneDTO.class)).thenReturn(Collections.singletonList(phoneDTO1));
        when(bms.mapTo(Collections.singletonList(phone2), PhoneDTO.class)).thenReturn(Collections.singletonList(phoneDTO2));

        Collection<PhoneDTO> foundPhones = phoneFacade.findPhonesByTechnicalInfo("good phone");
        Assert.assertEquals(1,foundPhones.size());
        Assert.assertTrue(foundPhones.contains(phoneDTO1));

        foundPhones = phoneFacade.findPhonesByTechnicalInfo("bad phone");
        Assert.assertEquals(1,foundPhones.size());
        Assert.assertTrue(foundPhones.contains(phoneDTO2));

    }

    @Test
    public void findPhonesByManufacturer() {
        when(phoneService.findPhonesByManufacturer(Manufacturer.APPLE)).thenReturn(Collections.singletonList(phone1));
        when(phoneService.findPhonesByManufacturer(Manufacturer.HUEAWEI)).thenReturn(Collections.singletonList(phone2));

        when(bms.mapTo(Collections.singletonList(phone1), PhoneDTO.class)).thenReturn(Collections.singletonList(phoneDTO1));
        when(bms.mapTo(Collections.singletonList(phone2), PhoneDTO.class)).thenReturn(Collections.singletonList(phoneDTO2));

        Collection<PhoneDTO> foundPhones = phoneFacade.findPhonesByManufacturer(Manufacturer.APPLE);
        Assert.assertEquals(1,foundPhones.size());
        Assert.assertTrue(foundPhones.contains(phoneDTO1));

        foundPhones = phoneFacade.findPhonesByManufacturer(Manufacturer.HUEAWEI);
        Assert.assertEquals(1,foundPhones.size());
        Assert.assertTrue(foundPhones.contains(phoneDTO2));

    }
    @Test
    public void createPhone() {
        Phone phone  = new Phone();

        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setId(3L);

        doAnswer(invocationOnMock ->
        {
            phone.setId(3L);
            return 3L;
        }).when(phoneService).createPhone(phone);

        when(bms.mapTo(phone, PhoneDTO.class)).thenReturn(phoneDTO);

        Assert.assertNotNull(phoneDTO.getId());
        Assert.assertEquals(3L, phoneDTO.getId().longValue());
    }
}
