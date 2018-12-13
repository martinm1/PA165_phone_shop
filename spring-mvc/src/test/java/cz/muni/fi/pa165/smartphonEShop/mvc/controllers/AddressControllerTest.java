package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;

import cz.muni.fi.pa165.smartphonEShop.dto.AddressCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.AddressDTO;
import cz.muni.fi.pa165.smartphonEShop.enums.AddressEnum;
import cz.muni.fi.pa165.smartphonEShop.facade.AddressFacade;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Stefan Holecko
 * Class represents: Tests for address mvc controller.
 */
@WebAppConfiguration
public class AddressControllerTest {
    @Mock
    private AddressFacade addressFacade;

    private AddressDTO addressDTO;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);

        AddressController addressController = new AddressController();
        addressController.setAddressFacade(addressFacade);

        mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();
    }

    @BeforeMethod
    public void testPrepare() {
        addressDTO = new AddressDTO();
        addressDTO.setId(111L);
    }

    @Test
    public void newAddressTest() throws Exception {
        this.mockMvc.perform(get("/address/new")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("addressCreate"))
                .andExpect(forwardedUrl("address/new"));
    }

    @Test
    public void viewTest() throws Exception {
        when(addressFacade.findAddressById(addressDTO.getId())).thenReturn(addressDTO);

        this.mockMvc.perform(get("/address/view/111")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("address", addressDTO))
                .andExpect(forwardedUrl("address/view"));
    }

    @Test
    public void createTest() throws Exception {
        AddressCreateDTO createDTO = new AddressCreateDTO();

        createDTO.setCity("Brno");
        createDTO.setCountry("Czechia");
        createDTO.setStreetName("Kroftova");
        createDTO.setStreetNumber("86");

        when(addressFacade.createAddress(createDTO)).thenReturn(111L);

        this.mockMvc.perform(post("/address/create")
                .flashAttr("addressCreate", createDTO)
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(flash().attributeExists("alert_success"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void listByCountryTest() throws Exception {
        List<AddressDTO> addresses = Collections.singletonList(addressDTO);

        HashMap<AddressEnum, String> specificatorCountry = new HashMap<>();
        specificatorCountry.put(AddressEnum.COUNTRY, "Czechia");
        when(addressFacade.findAllAddressesBy(specificatorCountry)).thenReturn(addresses);
        this.mockMvc.perform(get("/address/list/by?country=Czechia")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("addresses", addresses))
                .andExpect(forwardedUrl("address/list"));
    }

    @Test
    public void listByCityTest() throws Exception {
        List<AddressDTO> addresses = Collections.singletonList(addressDTO);

        HashMap<AddressEnum, String> specificatorCity = new HashMap<>();
        specificatorCity.put(AddressEnum.CITY, "Brno");
        when(addressFacade.findAllAddressesBy(specificatorCity)).thenReturn(addresses);
        this.mockMvc.perform(get("/address/list/by?city=Brno")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("addresses", addresses))
                .andExpect(forwardedUrl("address/list"));
    }

    @Test
    public void listByStreetNameTest() throws Exception {
        List<AddressDTO> addresses = Collections.singletonList(addressDTO);

        HashMap<AddressEnum, String> specificatorStreetName = new HashMap<>();
        specificatorStreetName.put(AddressEnum.STREET_NAME, "Kroftova");
        when(addressFacade.findAllAddressesBy(specificatorStreetName)).thenReturn(addresses);
        this.mockMvc.perform(get("/address/list/by?street=Kroftova")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("addresses", addresses))
                .andExpect(forwardedUrl("address/list"));
    }

    @Test
    public void listByStreetNumberTest() throws Exception {
        List<AddressDTO> addresses = Collections.singletonList(addressDTO);

        HashMap<AddressEnum,String> specificatorStreetNumber = new HashMap<>();
        specificatorStreetNumber.put(AddressEnum.STREET_NUMBER, "25");
        when(addressFacade.findAllAddressesBy(specificatorStreetNumber)).thenReturn(addresses);
        this.mockMvc.perform(get("/address/list/by?number=25")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("addresses", addresses))
                .andExpect(forwardedUrl("address/list"));
    }

    @Test
    public void listByCityCountryTest() throws Exception {
        List<AddressDTO> addresses = Collections.singletonList(addressDTO);

        HashMap<AddressEnum,String> specificatorCityCountry = new HashMap<>();
        specificatorCityCountry.put(AddressEnum.CITY, "Brno");
        specificatorCityCountry.put(AddressEnum.COUNTRY, "Czechia");
        when(addressFacade.findAllAddressesBy(specificatorCityCountry)).thenReturn(addresses);
        this.mockMvc.perform(get("/address/list/by?country=Czechia?city=Brno")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("addresses", addresses))
                .andExpect(forwardedUrl("address/list"));
    }

    @Test
    public void listByCityStreetNameTest() throws Exception {
        List<AddressDTO> addresses = Collections.singletonList(addressDTO);

        HashMap<AddressEnum,String> specificatorCityStreetName = new HashMap<>();
        specificatorCityStreetName.put(AddressEnum.CITY, "Brno");
        specificatorCityStreetName.put(AddressEnum.STREET_NAME, "Kroftova");
        when(addressFacade.findAllAddressesBy(specificatorCityStreetName)).thenReturn(addresses);
        this.mockMvc.perform(get("/address/list/by?city=Brno?street=Kroftova")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("addresses", addresses))
                .andExpect(forwardedUrl("address/list"));
    }

    @Test
    public void listByCityStreetNumberTest() throws Exception {
        List<AddressDTO> addresses = Collections.singletonList(addressDTO);

        HashMap<AddressEnum,String> specificatorCityStreetNumber = new HashMap<>();
        specificatorCityStreetNumber.put(AddressEnum.CITY, "Brno");
        specificatorCityStreetNumber.put(AddressEnum.STREET_NUMBER, "25");
        when(addressFacade.findAllAddressesBy(specificatorCityStreetNumber)).thenReturn(addresses);
        this.mockMvc.perform(get("/address/list/by?city=Brno?number=25")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("addresses", addresses))
                .andExpect(forwardedUrl("address/list"));
    }

    @Test
    public void listByCountryStreetNameTest() throws Exception {
        List<AddressDTO> addresses = Collections.singletonList(addressDTO);

        HashMap<AddressEnum,String> specificatorCountryStreetName = new HashMap<>();
        specificatorCountryStreetName.put(AddressEnum.STREET_NAME, "Kroftova");
        specificatorCountryStreetName.put(AddressEnum.COUNTRY, "Czechia");
        when(addressFacade.findAllAddressesBy(specificatorCountryStreetName)).thenReturn(addresses);
        this.mockMvc.perform(get("/address/list/by?country=Czechia?street=Kroftova")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("addresses", addresses))
                .andExpect(forwardedUrl("address/list"));
    }

    @Test
    public void listByCountryStreetNumberTest() throws Exception {
        List<AddressDTO> addresses = Collections.singletonList(addressDTO);

        HashMap<AddressEnum,String> specificatorCountryStreetNumber = new HashMap<>();
        specificatorCountryStreetNumber.put(AddressEnum.STREET_NUMBER, "25");
        specificatorCountryStreetNumber.put(AddressEnum.COUNTRY, "Czechia");
        when(addressFacade.findAllAddressesBy(specificatorCountryStreetNumber)).thenReturn(addresses);
        this.mockMvc.perform(get("/address/list/by?country=Czechia?number=25")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("addresses", addresses))
                .andExpect(forwardedUrl("address/list"));
    }

    @Test
    public void listByStreetNumberNameTest() throws Exception {
        List<AddressDTO> addresses = Collections.singletonList(addressDTO);

        HashMap<AddressEnum,String> specificatorStreetNumberName = new HashMap<>();
        specificatorStreetNumberName.put(AddressEnum.STREET_NUMBER, "25");
        specificatorStreetNumberName.put(AddressEnum.STREET_NAME, "Kroftova");
        when(addressFacade.findAllAddressesBy(specificatorStreetNumberName)).thenReturn(addresses);
        this.mockMvc.perform(get("/address/list/by?street=Kroftova?number=25")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("addresses", addresses))
                .andExpect(forwardedUrl("address/list"));

    }

    @Test
    public void listByCityCountryNameTest() throws Exception {
        List<AddressDTO> addresses = Collections.singletonList(addressDTO);

        HashMap<AddressEnum,String> specificatorCityCountryName = new HashMap<>();
        specificatorCityCountryName.put(AddressEnum.CITY, "Brno");
        specificatorCityCountryName.put(AddressEnum.COUNTRY, "Czechia");
        specificatorCityCountryName.put(AddressEnum.STREET_NAME, "Kroftova");
        when(addressFacade.findAllAddressesBy(specificatorCityCountryName)).thenReturn(addresses);
        this.mockMvc.perform(get("/address/list/by?country=Czechia?city=Brno?street=Kroftova")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("addresses", addresses))
                .andExpect(forwardedUrl("address/list"));
    }

    @Test
    public void listByCityCountryNumberTest() throws Exception {
        List<AddressDTO> addresses = Collections.singletonList(addressDTO);

        HashMap<AddressEnum,String> specificatorCityCountryNumber = new HashMap<>();
        specificatorCityCountryNumber.put(AddressEnum.CITY, "Brno");
        specificatorCityCountryNumber.put(AddressEnum.COUNTRY, "Czechia");
        specificatorCityCountryNumber.put(AddressEnum.STREET_NUMBER, "25");
        when(addressFacade.findAllAddressesBy(specificatorCityCountryNumber)).thenReturn(addresses);
        this.mockMvc.perform(get("/address/list/by?country=Czechia?city=Brno?number=25")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("addresses", addresses))
                .andExpect(forwardedUrl("address/list"));
    }

    @Test
    public void listByCityNameNumberTest() throws Exception {
        List<AddressDTO> addresses = Collections.singletonList(addressDTO);

        HashMap<AddressEnum,String> specificatorCityNameNumber = new HashMap<>();
        specificatorCityNameNumber.put(AddressEnum.CITY, "Brno");
        specificatorCityNameNumber.put(AddressEnum.STREET_NAME, "Kroftova");
        specificatorCityNameNumber.put(AddressEnum.STREET_NUMBER, "25");
        when(addressFacade.findAllAddressesBy(specificatorCityNameNumber)).thenReturn(addresses);
        this.mockMvc.perform(get("/address/list/by?city=Brno?street=Kroftova?number=25")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("addresses", addresses))
                .andExpect(forwardedUrl("address/list"));
    }

    @Test
    public void listByCountryNumberNameTest() throws Exception {
        List<AddressDTO> addresses = Collections.singletonList(addressDTO);

        HashMap<AddressEnum,String> specificatorCountryNumberName = new HashMap<>();
        specificatorCountryNumberName.put(AddressEnum.STREET_NAME, "Kroftova");
        specificatorCountryNumberName.put(AddressEnum.COUNTRY, "Czechia");
        specificatorCountryNumberName.put(AddressEnum.STREET_NUMBER, "25");
        when(addressFacade.findAllAddressesBy(specificatorCountryNumberName)).thenReturn(addresses);
        this.mockMvc.perform(get("/address/list/by?country=Czechia?street=Kroftova?number=25")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("addresses", addresses))
                .andExpect(forwardedUrl("address/list"));
    }

    @Test
    public void listByCityAll() throws Exception {
        List<AddressDTO> addresses = Collections.singletonList(addressDTO);

        HashMap<AddressEnum,String> specificatorAll = new HashMap<>();
        specificatorAll.put(AddressEnum.STREET_NAME, "Kroftova");
        specificatorAll.put(AddressEnum.COUNTRY, "Czechia");
        specificatorAll.put(AddressEnum.STREET_NUMBER, "25");
        specificatorAll.put(AddressEnum.CITY, "Brno");
        when(addressFacade.findAllAddressesBy(specificatorAll)).thenReturn(addresses);
        this.mockMvc.perform(get("/address/list/by?country=Czechia?city=Brno?street=Kroftova?number=25")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("addresses", addresses))
                .andExpect(forwardedUrl("address/list"));
    }

    @Test
    public void listByEmptyTest() throws Exception {
        List<AddressDTO> addresses = Collections.singletonList(addressDTO);

        HashMap<AddressEnum,String> specificatorEmpty = new HashMap<>();
        when(addressFacade.findAllAddressesBy(specificatorEmpty)).thenReturn(addresses);
        this.mockMvc.perform(get("/address/list/by")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("addresses", addresses))
                .andExpect(forwardedUrl("address/list"));
    }

}
