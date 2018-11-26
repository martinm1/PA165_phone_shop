package cz.muni.fi.pa165.smartphonEShop.service.facade;

import cz.muni.fi.pa165.smartphonEShop.dto.AddressDTO;
import cz.muni.fi.pa165.smartphonEShop.entity.Address;
import cz.muni.fi.pa165.smartphonEShop.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.smartphonEShop.service.service.AddressService;
import cz.muni.fi.pa165.smartphonEShop.service.service.BeanMappingService;
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
import java.util.List;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Created by Stefan Holecko
 * Class represents: Tests for addressFacade class
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class AddressFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private AddressService addressService;

    @Mock
    private BeanMappingService bms;

    @InjectMocks
    private AddressFacadeImpl addressFacade;

    private Address address1;
    private Address address2;

    private AddressDTO addressDTO1;
    private AddressDTO addressDTO2;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void testPrepare() {
        address1 = new Address();
        address2 = new Address();
        addressDTO1 = new AddressDTO();
        addressDTO2 = new AddressDTO();

        address1.setId(111L);
        address2.setId(222L);
        addressDTO1.setId(111L);
        addressDTO2.setId(222L);

        address1.setStreetNumber("25L");
        address2.setStreetNumber("66S");
        addressDTO1.setStreetNumber("25L");
        addressDTO2.setStreetNumber("66S");

        address1.setStreetName("Rooseveltova");
        address2.setStreetName("Rastislavova");
        addressDTO1.setStreetName("Rooseveltova");
        addressDTO2.setStreetName("Rastislavova");

        address1.setCity("Bratislava");
        address2.setCity("Praha");
        addressDTO1.setCity("Bratislava");
        addressDTO2.setCity("Praha");

        address1.setCountry("Slovakia");
        address2.setCountry("Czech republic");
        addressDTO1.setCountry("Slovakia");
        addressDTO2.setCountry("Czech republic");
    }

    @Test
    public void findAddressByIdTest() {
        when(addressService.findAddressById(111L)).thenReturn(address1);
        when(addressService.findAddressById(222L)).thenReturn(address2);
        when(bms.mapTo(address1, AddressDTO.class)).thenReturn(addressDTO1);
        when(bms.mapTo(address2, AddressDTO.class)).thenReturn(addressDTO2);

        Assert.assertEquals(addressDTO1, addressFacade.findAddressById(111L));
        Assert.assertEquals(addressDTO2, addressFacade.findAddressById(222L));
    }

    @Test
    public void findAllAddressesByTest() {
        //TODO

    }

    @Test
    public void createAddressTest() {
        Address address = new Address();

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(111L);

        doAnswer(invocationOnMock ->
        {
            address.setId(111L);
            return 111L;
        }).when(addressService).createAddress(address);

        when(bms.mapTo(address, AddressDTO.class)).thenReturn(addressDTO);

        System.out.println(addressDTO);

        Assert.assertNotNull(addressDTO.getId());
        Assert.assertEquals(111L, addressDTO.getId().longValue());

    }

    @Test
    public void getAllAddressesTest() {
        List<Address> addresses = Arrays.asList(address1, address2);
        List<AddressDTO> addressDTOList = Arrays.asList(addressDTO1, addressDTO2);

        when(addressService.getAllAddresses()).thenReturn(addresses);
        when(bms.mapTo(addresses, AddressDTO.class)).thenReturn(addressDTOList);

        Collection<AddressDTO> orders = addressFacade.getAllAddresses();

        Assert.assertEquals(2, orders.size());
    }


}
