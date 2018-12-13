package cz.muni.fi.pa165.smartphonEShop.rest;

import cz.muni.fi.pa165.smartphonEShop.RootWebContext;
import cz.muni.fi.pa165.smartphonEShop.dto.PhoneDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.Manufacturer;
import cz.muni.fi.pa165.smartphonEShop.facade.PhoneFacade;
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
 * Created by Stefan Holecko
 * Class represents: Tests for phones rest controller.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {RootWebContext.class})
public class PhonesControllerTest {
    @Mock
    private PhoneFacade phoneFacade;

    @Autowired
    @InjectMocks
    private PhonesController phonesController;

    private MockMvc mockMvc;

    private PhoneDTO phoneDTO;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(phonesController).setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @BeforeMethod
    public void testPrepare() {
        phoneDTO = new PhoneDTO();

        phoneDTO.setId(111L);
        phoneDTO.setManufacturer(Manufacturer.APPLE);
        phoneDTO.setModelName("iPhone 8 Plus");
        phoneDTO.setPrice(25555);
        phoneDTO.setTechnicalInfo("SuperMobil");
    }


}
