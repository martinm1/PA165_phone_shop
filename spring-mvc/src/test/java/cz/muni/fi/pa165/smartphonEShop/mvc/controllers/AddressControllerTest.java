package cz.muni.fi.pa165.smartphonEShop.mvc.controllers;

import cz.muni.fi.pa165.smartphonEShop.dto.AddressCreateDTO;
import cz.muni.fi.pa165.smartphonEShop.dto.AddressDTO;
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
    public void listAllTest() throws Exception {
        List<AddressDTO> addressDTOS = Collections.singletonList(addressDTO);

        when(addressFacade.getAllAddresses()).thenReturn(addressDTOS);

        this.mockMvc.perform(get("/address/list/all")
                .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("addressAll", addressDTOS))
                .andExpect(forwardedUrl("address/list"));
    }



}
