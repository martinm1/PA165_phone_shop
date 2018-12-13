package cz.muni.fi.pa165.smartphonEShop.rest;

import cz.muni.fi.pa165.smartphonEShop.RootWebContext;
import cz.muni.fi.pa165.smartphonEShop.dto.AddressDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.PhoneDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import cz.muni.fi.pa165.smartphonEShop.facade.AddressFacade;
import cz.muni.fi.pa165.smartphonEShop.facade.PhoneFacade;
import cz.muni.fi.pa165.smartphonEShop.rest.controllers.AddressesController;
import cz.muni.fi.pa165.smartphonEShop.rest.controllers.PhonesController;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by Jakub Ondrusek
 * Class represents: Tests for address rest controller.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {RootWebContext.class})
public class AddressControlerTest {
    @Mock
    private AddressFacade addressFacade;

    @Autowired
    @InjectMocks
    private AddressesController addressesController;

    private MockMvc mockMvc;

    private AddressDTO addressDTO;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(addressesController).setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @BeforeMethod
    public void testPrepare() {
       addressDTO = new AddressDTO();

       addressDTO.setId(111L);
       addressDTO.setCity("Brno");
       addressDTO.setCountry("cesko");
       addressDTO.setStreetName("hrncirska");
       addressDTO.setStreetNumber("bohviekolko");
    }

}
